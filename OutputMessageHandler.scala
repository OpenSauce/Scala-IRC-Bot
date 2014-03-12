import java.io._
import java.net._
import scala.actors.Actor
import scala.actors.Actor._

class OutputMessageHandler(outputStream: BufferedWriter) extends Actor {
  case object Stop
  
  def sendMessage(message: String) {
      outputStream.write(message)
      outputStream.newLine
      outputStream.flush
  }
  
  def act { 
    loop {
      react {
        case Message(contents) =>
          sendMessage(contents)
          println("Message received")
        case Stop =>
          exit
      }
    }
  }
  
}