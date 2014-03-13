import java.io._
import java.net._

class InboundMessageHandler(inputStream: BufferedReader, outboundMessageHandler: OutboundMessageHandler) {
  var isRunning: Boolean = false
  
  def startListening {
    isRunning = true
    listenLoop
  }
  
  def stopListening {
     isRunning = false
  }
  
  def listenLoop {
    while(isRunning) {
      val line = inputStream.readLine
      if(line != null) {
        outboundMessageHandler ! CommandFactory.createResponse(parseMessage(line))
      }
    }
  }
  
  def parseMessage(inputLine: String): Message = {
    null
  } 
  
}