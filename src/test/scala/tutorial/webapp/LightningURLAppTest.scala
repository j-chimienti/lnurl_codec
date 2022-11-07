package tutorial.webapp

import utest._

import scala.scalajs.js

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.ext._

object LightningURLAppTest extends TestSuite {

  // Initialize App
  LightningURLApp.setupUI()

  def tests = Tests {
    test("header") {
      assert(document.querySelectorAll("h1").count(_.textContent == "LNURL Codec") == 1)
    }

    test("decode") {

    }

//    test("ButtonClick") {
//      def messageCount =
//        document.querySelectorAll("p").count(_.textContent == "You clicked the button!")
//
//      val button = document.querySelector("button").asInstanceOf[dom.html.Button]
//      assert(button != null && button.textContent == "Click me!")
//      assert(messageCount == 0)
//
//      for (c <- 1 to 5) {
//        button.click()
//        assert(messageCount == c)
//      }
//    }
  }
}
