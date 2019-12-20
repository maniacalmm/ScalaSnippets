import Dependencies._

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "ScalaSnippets",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.4",
    libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.4" % "provided",
    libraryDependencies += "com.github.javafaker" % "javafaker" % "1.0.1",
      // https://mvnrepository.com/artifact/org.apache.spark/spark-avro,
    libraryDependencies += "org.apache.spark" %% "spark-avro" % "2.4.4",
      // https://mvnrepository.com/artifact/com.google.inject/guice
    libraryDependencies += "com.google.inject" % "guice" % "4.0"


)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
