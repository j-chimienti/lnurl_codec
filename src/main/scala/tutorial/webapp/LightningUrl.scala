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

  def five2eight(input: Array[Byte]): Array[Byte] = {
    var buffer = 0L
    val output = collection.mutable.ArrayBuffer.empty[Byte]
    var count = 0
    input.foreach(b => {
      buffer = (buffer << 5) | (b & 31)
      count = count + 5
      while (count >= 8) {
        output.append(((buffer >> (count - 8)) & 0xff).toByte)
        count = count - 8
      }
    })
    require(count <= 4, "Zero-padding of more than 4 bits")
    require((buffer & ((1 << count) - 1)) == 0, "Non-zero padding in 8-to-5 conversion")
    output.toArray
  }
  def decode(url: String): String = {
    Bech32.decode(url) match {
      case Failure(exception) => exception.getMessage
      case Success(value) =>
        new String(five2eight(value._2.toArray))
    }
  }

  def eight2five(input: Array[Byte]): Array[Byte] = {
    var buffer = 0L
    val output = collection.mutable.ArrayBuffer.empty[Byte]
    var count = 0
    input.foreach(b => {
      buffer = (buffer << 8) | (b & 0xff)
      count = count + 8
      while (count >= 5) {
        output.append(((buffer >> (count - 5)) & 31).toByte)
        count = count - 5
      }
    })
    if (count > 0) output.append(((buffer << (5 - count)) & 31).toByte)
    output.toArray
  }
  val hrp = "lnurl"
  def apply(uri: String): LightningUrl = {
    val e = Bech32.encode( hrp, eight2five(uri.getBytes()))
    new LightningUrl(e.get)
  }
}
