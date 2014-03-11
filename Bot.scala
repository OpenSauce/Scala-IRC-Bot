import java.io._
import java.net._
import scala.actors.Actor
import scala.actors.Actor._

class Bot(serverAddress: String = "irc.w3.org", serverPort: Int = 6667, nick: String = "DickBot") extends Actor {
  var connected: Boolean = false
  var outputStream: BufferedWriter = _
  var inputStream: BufferedReader = _
  
  def connectToServer {
    var serverSocket = new Socket(serverAddress, serverPort)
    this.outputStream = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream))
    this.inputStream = new BufferedReader(new InputStreamReader(serverSocket.getInputStream))
    this.connected = true;
      
    sendMessage("NICK " + this.nick)
    sendMessage("USER " + this.nick + " DickBot DickBot Dickbot")
    sendMessage("JOIN #ectest900")
    this.start
  }
  
     def pong(dataSplit: String) {
      if(dataSplit.substring(0,4).equalsIgnoreCase("ping")) {
         val pongmsg = "pong " + dataSplit.substring(5);
         sendMessage(pongmsg);
         println(pongmsg);
      }
   }
  
  def sendMessage(message: String): Boolean = {
    if (connected) {
      outputStream.write(message)
      outputStream.newLine
      outputStream.flush
      true
    } else {
      false
    }
  }
  
  def act {
    loop {
      val line = inputStream.readLine
      if(line != null) {
        val dataSplit = line.split(":");
            if(dataSplit(0).equals("PING"))
            {
               pong(line)
            }
            else
            {
               println(line);
            }
      }
    }
  }
  
}