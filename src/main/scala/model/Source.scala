package model

import org.apache.spark.sql.{Encoder, Encoders}


case class Source (
                   jobId: BigInt,
                   agency: String,
                   postingType: String,
                   noPositions: Int,
                   businessTitle: String,
                   civilServiceTitle: String,
                   titleCode: String,
                   level: String,
                   salaryRangeFrom: BigInt,
                   salaryRangeTo: BigInt
                 )
object Source {

  implicit val source: Encoder[Source] = Encoders.product[Source]

}