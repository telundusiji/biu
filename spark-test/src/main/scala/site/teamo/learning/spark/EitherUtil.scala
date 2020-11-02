package site.teamo.learning.spark

/**
 * @author 爱做梦的锤子
 * @create 2020/9/2
 */
class EitherUtil {
}

object EitherUtil {
  def main(args: Array[String]): Unit = {
    val result = safe(0, (input: Int) => 10 / input)
    result match {
      case Left(output) => println(output)
      case Right((input, e)) => println(input, e)
    }
    val result1 = safe((input: Int) => 10 / input)(10)
    result1 match {
      case Left(output) => println(output)
      case Right((input, e)) => println(input, e)
    }
  }
  def safe[I, O](input: I, f: I => O): Either[O, (I, Exception)] = {
    try {
      val result = f(input)
      Left(result)
    } catch {
      case e: Exception => Right(input, e)
    }
  }
  def safe[I, O](f: I => O): I => Either[O, (I, Exception)] = {
    new Function[I, Either[O, (I, Exception)]]() {
      override def apply(input: I): Either[O, (I, Exception)] = {
        try {
          val result = f(input)
          Left(result)
        } catch {
          case e: Exception => Right(input, e)
        }
      }
    }
  }
}
