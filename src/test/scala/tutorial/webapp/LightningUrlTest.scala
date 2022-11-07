package tutorial.webapp

import utest._

object LightningUrlTest extends TestSuite {
  override def tests: Tests = Tests {
    test("decode") {
      // lnurl from bitrefill
      val url = "lnurl1dp68gurn8ghj7cnfw3ex2enfd3kzumt99amrztmvde6hymzlwpshjtekxp3kxephvd3rve3kx4jkyvpsxpnrzvp3vymnydq84ec"
      val u = LightningUrl.decode(url)
      assert(u.get == "https://bitrefill.me/v1/lnurl_pay/60ccd7cb6f65eb000f101a72")
    }
  }
}
