package site.teamo.learning.spark

import org.apache.spark.sql.Row
import org.apache.spark.{SparkConf, SparkContext}

object SparkTest {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
      .setAppName("SparkTest")
      .setMaster("local[*]")
    val sparkContext = new SparkContext(sparkConf)

    val rdd = sparkContext.makeRDD(List(
      Row(1, "a")
    ))
    rdd.foreach(r=>println(r))

    sparkContext.stop()
  }

}
