import java.io._
import java.net._
import scala.actors.Actor
import scala.actors.Actor._

class Bot(serverAddress: String = "irc.w3.org", serverPort: Int = 6667, val nick: String = "DickBot") {
  var outboundMessageHandler: OutboundMessageHandler = _
  var inboundMessageHandler: InboundMessageHandler = _
  
  def connectToServer {
    val serverSocket = new Socket(serverAddress, serverPort)
    val outputStream = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream))
    val inputStream = new BufferedReader(new InputStreamReader(serverSocket.getInputStream))
    this.outboundMessageHandler = new OutboundMessageHandler(outputStream)
    this.inboundMessageHandler = new InboundMessageHandler(inputStream, outboundMessageHandler)
      
    setupConnection
  }
  
  def setupConnection {
    outboundMessageHandler.connect(this)
    outboundMessageHandler.start  
    inboundMessageHandler.startListening
  }
   
}