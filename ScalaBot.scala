
object ScalaBot {
  def main(args: Array[String]) {
    val bot = new Bot("irc.w3.org", 6667, "DickBot")
    bot.connectToServer
  }
}