package site.teamo.learning.spark

import java.io.BufferedReader

import scala.tools.nsc.{Interpreter, Settings}
import scala.tools.nsc.interpreter.{ILoop, JPrintWriter}

/**
 * @author 爱做梦的锤子
 * @create 2020/11/2
 */
class ScalaCompiler(in0: Option[BufferedReader], out: JPrintWriter) extends ILoop(in0, out) {

  def this(in0: BufferedReader, out: JPrintWriter) = this(Some(in0), out)

  def this() = this(None, new JPrintWriter(Console.out, true))

  def initializeSpark() {
    intp.beQuietDuring {
      processLine(
        """
         val a = 10
         println(a)
        """)
    }
  }

}
object ScalaCompiler{
  def main(args: Array[String]): Unit = {
    var i = new Interpreter(new Settings(str => println(str)))
    i.interpret("import java.lang.Object")
    i.interpret("class Test { def hello = \"Hello World\"}")
  }
}
