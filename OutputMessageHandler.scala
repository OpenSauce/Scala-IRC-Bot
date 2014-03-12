import java.io._
import java.net._

class OutputMessageHandler(outputStream: BufferedWriter) extends MessageHandler {
  
  def sendMessage(message: String) {
      outputStream.write(message)
      outputStream.newLine
      outputStream.flush
  }
  
  def act { 
    
  }
  
}