package tutorial.webapp

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.html.TextArea
import scalatags.JsDom.short._

import scala.util.{Failure, Success}


object LightningURLApp {
  def main(args: Array[String]): Unit = {
    document.addEventListener("DOMContentLoaded", { (e: dom.Event) =>
      setupUI()
    })
  }



  def setupUI(): Unit = {
    val title = h1("LNURL Codec")
    val reasonInput = textarea(*.id := "reasonInput", *.rows:="5", *.cls:="form-control", *.placeholder:="LNURL *").render
    val decoded = textarea(*.id := "decoded", *.rows:="5", *.cls:="form-control", *.readonly).render
    def decodeInput() = {
      val v = reasonInput.render.value
      val trimmed = v.replace("lightning:", "").trim
      val r = LightningUrl.decode(trimmed)
      decoded.render.textContent = r match {
        case Failure(exception) => s"Error: " + exception.getMessage
        case Success(value) =>value
      }
    }
    val decodeBtn = button(*.cls:="btn btn-primary",
      *.id := "decodeBtn",
      *.onclick:= {
      () => decodeInput()
    })("decode")

    def encode(v: String, out: TextArea) = {
      val d = LightningUrl(v).url
      out.render.textContent = d
    }
    val encodedOutput = textarea(*.id := "encodedOutput", *.rows:="5", *.cls:="form-control", *.placeholder:="encoded", *.readonly).render
    val encodeInput = textarea(
      *.id := "encodeInput", *.rows:="5",
      *.cls:="form-control",
      *.placeholder:="string to encode",
//      *.oninput:= {
//        (e: String) => {
//          println("changed")
//          encode(e, encodedOutput)
//        }
//      }
    ).render



    val encodeBtn = button(*.cls:="btn btn-primary", *.onclick:= {
      () => encode(encodeInput.value, encodedOutput)
    })("encode")


    val page = {
      div(*.cls:="container text-center")(
        title,
      div(*.cls:= "row d-flex justfiy-content-center")(
        div(*.cls:="col-md-6")(reasonInput),
        div(*.cls:="col-md-6")(decoded)

      ),
        div(*.cls:="row my-4")(
          decodeBtn
        ),
        hr(),
        div(*.cls:= "row d-flex justfiy-content-center")(
          div(*.cls:="col-md-6")(encodeInput),
          div(*.cls:="col-md-6")(encodedOutput)
        ),
        div(*.cls:="row my-4")(
          encodeBtn
        ),
        div(*.cls:="row my-4")(
          a(*.href:="https://github.com/j-chimienti/lnurl_codec")(
            "https://github.com/j-chimienti/lnurl_codec"
          )
        )
      )
    }
    document.body.appendChild(page.render)
  }

}
