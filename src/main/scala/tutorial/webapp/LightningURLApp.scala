package tutorial.webapp

import org.scalajs.dom
import org.scalajs.dom.document
import scalatags.JsDom.short._


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
      println(s"decode ${v}")
      val trimmed = v.replace("lightning:", "").trim
      val r = LightningUrl.decode(trimmed)
      decoded.render.textContent = r
    }
    val decodeBtn = button(*.cls:="btn btn-primary", *.onclick:= {
      () => decodeInput()
    })("decode")

    val encodeInput = textarea(*.id := "encodeInput", *.rows:="5", *.cls:="form-control", *.placeholder:="string to encode").render
    val encodedOutput = textarea(*.id := "encodedOutput", *.rows:="5", *.cls:="form-control", *.placeholder:="encoded", *.readonly).render
    def encode = {
      val v = encodeInput.render.value
      val d = LightningUrl(v).url
      encodedOutput.render.textContent = d
    }

    val encodeBtn = button(*.cls:="btn btn-primary", *.onclick:= {
      () => encode
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
