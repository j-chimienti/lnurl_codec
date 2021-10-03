package tutorial.webapp

import utest._

import scala.scalajs.js

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.ext._

object LightningURLAppSpec extends TestSuite {

  // Initialize App
  LightningURLApp.setupUI()

  val url = "https://service.com/api?q=3fc3645b439ce8e7f2553a69e5267081d96dcd340693afabe04be7b0ccd178df"
  val encoded = "LNURL1DP68GURN8GHJ7UM9WFMXJCM99E3K7MF0V9CXJ0M385EKVCENXC6R2C35XVUKXEFCV5MKVV34X5EKZD3EV56NYD3HXQURZEPEXEJXXEPNXSCRVWFNV9NXZCN9XQ6XYEFHVGCXXCMYXYMNSERXFQ5FNS".toLowerCase
  def tests = Tests {

    test("encode") {
      document.querySelector("#encodeInput").textContent = url

      val button = document.querySelector("#encodeBtn").asInstanceOf[dom.html.Button]
      assert(button != null && button.textContent == "encode")
        button.click()

      assert(document.querySelector("#encodedOutput").textContent == encoded)
    }
    test("decode") {
      document.querySelector("#decodeInput").textContent = encoded

      val button = document.querySelector("#decodeBtn").asInstanceOf[dom.html.Button]
      assert(button != null && button.textContent == "decode")
      button.click()


      assert(document.querySelector("#decodedOutput").textContent == url)
    }
  }
}
