import java.io._
import java.net._
import scala.actors.Actor
import scala.actors.Actor._

class Bot(serverAddress: String = "irc.w3.org", serverPort: Int = 6667, nick: String = "DickBot") {
  var outputMessageHandler: OutputMessageHandler = _
  var inputMessageHandler: InputMessageHandler = _
  case object Stop
  
  def connectToServer {
    val serverSocket = new Socket(serverAddress, serverPort)
    val outputStream = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream))
    val inputStream = new BufferedReader(new InputStreamReader(serverSocket.getInputStream))
    this.outputMessageHandler = new OutputMessageHandler(outputStream)
    this.inputMessageHandler = new InputMessageHandler(inputStream, outputMessageHandler)
      
    setupConnection
  }
  
  def setupConnection {
    outputMessageHandler.sendMessage("NICK " + this.nick)
    outputMessageHandler.sendMessage("USER " + this.nick + " DickBot DickBot Dickbot")
    outputMessageHandler.sendMessage("JOIN #ectest900")
    inputMessageHandler.startActor
  }
   
}