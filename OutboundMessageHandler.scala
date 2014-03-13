import java.io._
import java.net._
import scala.actors.Actor
import scala.actors.Actor._

class OutboundMessageHandler(outputStream: BufferedWriter) extends Actor {
  case object Stop
  
  private def sendMessage(message: String) {
      outputStream.write(message)
      outputStream.newLine
      outputStream.flush
  }
  
  def connect(bot: Bot) {
    sendMessage("NICK " + bot.nick)
    sendMessage("USER " + bot.nick + " DickBot DickBot Dickbot")
    sendMessage("JOIN #Test")
  }
  
  def act { 
    loop {
      react {
        case OutboundMessage =>
          sendMessage(OutboundMessage.toString)
        case Stop =>
          exit
      }
    }
  }
  
}