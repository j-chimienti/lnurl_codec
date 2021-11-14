package tutorial.webapp


import scala.util.Try

case class LightningUrl(url: String) {
  // validate
  Try(Bech32.decode(url)).failed.foreach(err => {
    throw new IllegalArgumentException(s"Invalid url $url $err")
  })
  override def toString: String = url

}
object LightningUrl {
  val hrp = "lnurl"
  def decode(l: LightningUrl): Try[String] = decode(l.url)
  def decode(url: String): Try[String] =
    Bech32.decode(url) map { value =>
      new String(Bech32.from5Bit(value._2).toArray)
    }

  def apply(uri: String): LightningUrl = {
    val e = Bech32.encode(hrp, Bech32.to5Bit(uri.getBytes()))
    new LightningUrl(e.get)
  }
}
