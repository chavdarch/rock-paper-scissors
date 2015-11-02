package example.rock.paper.scissors

import java.io._

import org.scalatest.{FlatSpec, Matchers}
import scala.Console

trait InputOutputTestUtils {
  def withOutput(out: OutputStream = new ByteArrayOutputStream()): Output = new Output {
    override def writer: PrintStream = new PrintStream(out)
  }

  def withInput(input:String) = new Input {
    val stringReader: StringReader = new StringReader(input)
    override def reader: BufferedReader = new BufferedReader(stringReader)
  }
}

/**
 * Created by cchernashki on 02/11/2015.
 */
class InputOutputSpec  extends FlatSpec with Matchers with InputOutputTestUtils {


  "An Input" should "allow reading from an input reader" in {
    withInput("testString").read() should be("testString")
  }

  "An Output" should "allow printing to a writer" in {
    val outStream = new ByteArrayOutputStream()

    withOutput(outStream).print("testString")
    outStream.toString should be("testString")
  }

  "A ConsoleInputOutput" should "allow reading to and writing from console" in {
    Console.withIn(new StringReader("testString")) {
      val inAndOut = new ConsoleInputOutput {}
      inAndOut.read() should be("testString")
    }

    val writer = new java.io.ByteArrayOutputStream()
    Console.withOut(writer) {
      val inAndOut = new ConsoleInputOutput {}
      inAndOut.print("testPrint")
      writer.toString should be ("testPrint")
    }
  }
}
