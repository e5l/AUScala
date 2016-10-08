import java.nio.file.NoSuchFileException

import scala.io.Source

/*
TODO Прочитайте содержимое данного файла.
В случае неудачи верните сообщение соответствующего исключения.
 */

def readThisWorksheet(): String =
  Source
    .fromFile("/home/user/Documents/Scala/FirstTask/src/main/scala/HomeTask.sc")
    .mkString

try {
  println(readThisWorksheet())
} catch {
  case e: NoSuchFileException => println(s"file not found: ${e.getMessage}")
  case e: Exception => println(s"Unknown error: ${e.getMessage}")
}
