import java.io._
import java.net._

class InputMessageHandler(inputStream: BufferedReader, outputHandler: OutputMessageHandler) extends MessageHandler {
  var isRunning: Boolean = false
  case object Stop
  
    
  def startActor {
    this.start
    isRunning = true
  }
  
  def stopActor {
     isRunning = false
  }
  
  def act {
    while(isRunning) {
      val line = inputStream.readLine
      if(line != null) {
        parseLine(line)
      }
    }
  }
  
  def parseLine(inputLine: String) {
    println(inputLine)
    val splitString = inputLine.split(" ")
    if(splitString(0).equalsIgnoreCase("PING")) {
      outputHandler.sendMessage("PONG " + splitString(1))
    }
  }
  
}