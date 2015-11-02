package example.rock.paper.scissors

import java.io.{BufferedReader, PrintStream}


trait Output {
  def writer: PrintStream

  def print(s: String) = writer.print(s)

  def println(s: String) = writer.println(s)

}

object Input {
  val validParticipantTypes: Seq[String] = Seq("player", "computer")
}

trait Input {
  def reader: BufferedReader

  def read(): String = reader.readLine()
}

trait ConsoleInputOutput extends Input with Output {
  override val reader: BufferedReader = Console.in
  override val writer: PrintStream = Console.out
}