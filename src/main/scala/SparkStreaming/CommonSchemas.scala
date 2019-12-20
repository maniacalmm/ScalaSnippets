package SparkStreaming

import java.sql.Timestamp

object CommonSchemas {

  case class sales(timestamp: Timestamp, name: String, address: String, currency: String, amount: Double)

}
