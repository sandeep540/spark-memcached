import com.typesafe.scalalogging.LazyLogging
import model.Source
import net.spy.memcached.{AddrUtil, MemcachedClient}
import org.apache.spark.sql.{DataFrame, Dataset, Encoders, SaveMode, SparkSession}

import java.util.concurrent.TimeUnit

object Application  extends App with LazyLogging {


  val spark = SparkSession.builder
    .master("local[*]")
    .appName("Test")
    .getOrCreate()

  val ds: Dataset[Source] = spark.read
    .format("csv")
    .option("header", "false") //first line in file has headers
    .option("charset", "UTF8")
    .option("delimiter",",")
    .schema(Encoders.product[Source].schema)
    .load("data_analysis_jobs.csv")
    .as[Source]

  import org.apache.spark.sql.functions._
  import spark.implicits._

  val cacheMapDf: DataFrame = ds
                .groupBy("jobId")
                .agg(collect_list(struct("postingType", "noPositions", "businessTitle", "civilServiceTitle", "titleCode", "level", "salaryRangeFrom", "salaryRangeTo")).alias("data"))
                .toDF("jobId", "jobData")
                .toJSON
                .withColumn("cacheKey", get_json_object($"value", "$.jobId"))
                .withColumn("cacheValue", get_json_object($"value", "$.jobData"))


  logger.info(s" Cache Key and Map value Created  ")
  logger.info(s" Start Posting data to Cache  ")

  val cacheClient = new MemcachedClient(AddrUtil.getAddresses("localhost:11211"))

  cacheMapDf.collect.foreach { row =>
    cacheClient.set(row.getAs[String]("cacheKey"), 604800, row.getAs[String]("cacheValue")).get(1000, TimeUnit.MILLISECONDS)
  }



  spark.stop()
  System.exit(0)

}
