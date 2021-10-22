name := "spark-memcached"

version := "0.1"

scalaVersion := "2.12.14"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.2.0" % "compile",
  "org.apache.spark" %% "spark-sql" % "3.2.0" % "compile",
  "net.spy" % "spymemcached" % "2.12.3",
  "com.typesafe" % "config" % "1.4.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4"
)