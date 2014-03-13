import java.util.ArrayList

case class InboundMessage(command: String, sender: String, destination: String, trailing: String, messageParameters: ArrayList[String]) 
  extends Message(command, destination, trailing, messageParameters) {
  
}