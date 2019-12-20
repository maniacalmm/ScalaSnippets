package SparkStreaming

import org.apache.spark.sql.{Dataset, SaveMode, SparkSession}

object Utils {


  def saveToLocalTmp[T](spark: SparkSession,
                        df: Dataset[T],
                        fileName: String,
                        format: String="parquet",
                        saveMode: SaveMode = SaveMode.Append,
                        partitionBy: List[String] = List()): Unit = {

    partitionBy.size match {
      case 0 => df.write.mode(saveMode).format(format).save(s"/tmp/${fileName}")
      case _ => df.write.mode(saveMode).partitionBy(partitionBy: _*).format(format).save(s"/tmp/${fileName}")
    }
  }



}
