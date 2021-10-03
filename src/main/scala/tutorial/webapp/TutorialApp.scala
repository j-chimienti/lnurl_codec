package tutorial.webapp

import org.scalajs.dom
import org.scalajs.dom.document
import scalatags.JsDom.short._


object TutorialApp {
  def main(args: Array[String]): Unit = {
    document.addEventListener("DOMContentLoaded", { (e: dom.Event) =>
      setupUI()
    })
  }

  def decode(input: String) = LightningUrl.decode(input)

  def setupUI(): Unit = {
    val title = h4("LNURL Decoder")
    val reasonInput = textarea(*.id := "reasonInput").render
    val decoded = textarea(*.id := "decoded").render
    val button = document.createElement("button")
    button.textContent = "Decode"
    button.addEventListener("click", { (e: dom.MouseEvent) =>
    println(s"decode ${reasonInput.value}")
      val trimmed = reasonInput.value.replace("lightning:", "").trim
      val r = decode(trimmed)
      decoded.textContent = r
    })
    val page = div(
      title,
      div(
        reasonInput,
        decoded
      ),
      button
    )
    document.body.appendChild(page.render)
  }

}
