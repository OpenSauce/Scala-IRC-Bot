import java.util.ArrayList

case class OutboundMessage(command: String, trailing: String, destination: String, messageParameters: ArrayList[String]) 
  extends Message(command, destination, trailing, messageParameters) {
  
}