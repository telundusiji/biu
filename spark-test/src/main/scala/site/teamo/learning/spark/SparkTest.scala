package site.teamo.learning.spark

import org.apache.spark.sql.Row
import org.apache.spark.{SparkConf, SparkContext}

object SparkTest {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
      //设置Spark运行方式为local方式
      .setMaster("local[*]")
    val sparkContext = new SparkContext(sparkConf)

    //读取hdfs上文件
    val sourceFile = sparkContext.textFile("hdfs://spark11:8020/data.txt")
    //对读取的内容进行按空格分隔后在统计词频
    val resultRdd = sourceFile
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey((curr, agg) => curr + agg)
    //从rdd中获取结果并打印
    val result = resultRdd.collect()
    result.foreach(r=>println(r))
    sparkContext.stop()
  }

}
