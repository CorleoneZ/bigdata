package com.bigdata.scalaDemo

import scala.collection.mutable
import org.json4s.DefaultFormats
import org.json4s.jackson.Serialization.write

object Map2Json {

  private implicit val formats: DefaultFormats.type = org.json4s.DefaultFormats

  def main(args: Array[String]): Unit = {
    val map = mutable.Map[String, Any]("id"->"asasd","event"->"ioquwnasdji","time"->1658827171668L,"ss"->None)

    println(write(map))
  }

}
