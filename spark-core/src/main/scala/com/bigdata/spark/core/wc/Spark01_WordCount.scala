package com.bigdata.spark.core.wc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/*
val textFile = sc.textFile("file:///opt/spark/job/words.txt")
val counts = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
counts.collect
 */
object Spark01_WordCount {
  def main(args: Array[String]): Unit = {

    //application   spark框架

    // TODO 建立和spark框架连接
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    // TODO 执行业务操作
    /**
     * 1.read file row by row
     * 2.row transform word
     * 3.group and count
     * 4.aggregation transform
     * 5.print result
     */
    val lines : RDD[String] = sc.textFile("com.jpa.data")
    val words : RDD[String] = lines.flatMap(_.split(" "))
    val wordGroup : RDD[(String, Iterable[String])] = words.groupBy(word=>word)

    val wordToCount = wordGroup.map {
      case (word, list) =>
        (word, list.size)
    }

    val array: Array[(String, Int)] = wordToCount.collect()
    array.foreach(println)

    // TODO 关闭连接
    sc.stop()
  }
}
