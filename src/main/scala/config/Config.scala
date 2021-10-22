package config


import com.typesafe.config.{Config, ConfigFactory}

import scala.util.Try

object Config {
  def config: Config = _config.get

  private val _config = Try {
    val cfg = ConfigFactory.load()
    cfg
  }

  val sparkAppName: String = config.getString("spark.context-settings.spark.app.name")

  val cache_nodes: String = config.getString("cache.nodes")
  val cache_ttl: Int = config.getInt("cache.ttl")

}

