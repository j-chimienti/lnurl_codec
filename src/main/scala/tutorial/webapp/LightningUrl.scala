package tutorial.webapp


import scala.util.{Failure, Success, Try}

case class LightningUrl(url: String) {
  // validate
  Try(Bech32.decode(url)).failed.foreach(err => {
    throw new IllegalArgumentException(s"Invalid url $url $err")
  })
  override def toString: String = url

}
object LightningUrl {
  def decode(l: LightningUrl): String = decode(l.url)
  def decode(url: String): String = {
    Bech32.decode(url) match {
      case Failure(exception) => exception.getMessage
      case Success(value) =>
        new String(Bech32.from5Bit(value._2).toArray)
    }
  }

  val hrp = "lnurl"
  def apply(uri: String): LightningUrl = {
    val e = Bech32.encode(hrp, Bech32.to5Bit(uri.getBytes()))
    new LightningUrl(e.get)
  }
}
