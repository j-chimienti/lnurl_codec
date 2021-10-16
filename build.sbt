enablePlugins(ScalaJSPlugin)
// https://getbootstrap.com/docs/3.4/getting-started/
name := "LNURL Codec"
scalaVersion := "2.13.5"

libraryDependencies += "com.lihaoyi" %%% "scalatags" % "0.9.4"

//lazy val root = (project in file("."))
// This is an application with a main method
scalaJSUseMainModuleInitializer := true

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.1.0"

// Add support for the DOM in `run` and `test`
jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()

// uTest settings
libraryDependencies += "com.lihaoyi" %%% "utest" % "0.7.9" % "test"
testFrameworks += new TestFramework("utest.runner.Framework")

