package infrastructure

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

trait Json {

  val objectMapper = new ObjectMapper with ScalaObjectMapper
  objectMapper.registerModule(DefaultScalaModule)
  objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def toJson(value: Any): String = {
    objectMapper.writeValueAsString(value)
  }

  def fromJson[T](json: String)(implicit m: Manifest[T]): T = {
    objectMapper.readValue[T](json)
  }
}
