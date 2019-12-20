package SparkStreaming

import java.sql.Timestamp
import java.time.LocalDateTime

import SparkStreaming.CommonSchemas.sales
import com.github.javafaker.Faker
import org.apache.spark.sql.{Dataset, Encoders, SaveMode, SparkSession}

import scala.util.Random
import org.apache.spark.sql.functions._

object RandomSalesGen {

  def generateOneRecord(faker: Faker): sales = {
    val timestamp = Timestamp.valueOf(LocalDateTime.now().minusMinutes(Random.nextLong() % 240))
    sales(timestamp, faker.name().firstName(), faker.address().fullAddress(), faker.currency().code(), Random.nextDouble() % 1000)
  }

  def generateRandomNumberOfSalesRecord(spark: SparkSession, low: Int = 100, range: Int = 1000): Dataset[sales] = {
    val faker = new Faker()
    import spark.implicits._
    val numOfRecords = Math.abs(Random.nextInt() % range + low)
    println("numOfRecords: ", numOfRecords)
    (1 to numOfRecords).map(_ => generateOneRecord(faker)).toSeq.toDS()
  }

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().master("local").getOrCreate()

//    while (true) {
//
//      val df = generateRandomNumberOfSalesRecord(spark, 1, 10)
//
//      val df_p = df
//        .withColumn("date", date_format(col("timestamp"), "YYYY-MM-dd"))
//        .withColumn("hour", date_format(col("timestamp"), "HH"))
//
//      Utils.saveToLocalTmp(spark, df_p, "fake_sale_avro", "avro", SaveMode.Append, List("date", "hour"))
//
//      Thread.sleep(5000)
//    }

    val df = spark.read.format("avro").load("/tmp/fake_sale_avro")
    Utils.saveToLocalTmp(spark, df, "fake_sale_compact", "avro", SaveMode.Append, List("date", "hour"))
//    val res = spark.read.format("avro").load("/tmp/fake_sales")


  }

}
