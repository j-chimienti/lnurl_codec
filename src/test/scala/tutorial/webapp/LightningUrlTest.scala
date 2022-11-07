package tutorial.webapp

import utest._

object LightningUrlTest extends TestSuite {
  override def tests: Tests = Tests {
    test("decode") {
      // lnurl from bitrefill
      val (url, decodedUrl) = TestVectors.LNUrls.bitrefillUrl
      val u = LightningUrl.decode(url)
      assert(u.get == decodedUrl)
    }
  }
}
