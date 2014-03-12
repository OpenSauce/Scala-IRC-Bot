import java.io._
import java.net._

class InputMessageHandler(inputStream: BufferedReader, outputHandler: OutputMessageHandler) {
  var isRunning: Boolean = false
  
  def startListening {
    isRunning = true
    listenLoop
  }
  
  def stopActor {
     isRunning = false
  }
  
  def listenLoop {
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
      outputHandler ! new Message("PONG " + splitString(1))
    }
  }
  
}