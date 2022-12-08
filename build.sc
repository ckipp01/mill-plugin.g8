import $ivy.`io.chris-kipp::mill-giter8::0.2.2`

// These are just in here for Steward
import $ivy.`com.goyeau::mill-scalafix::0.2.11`
import $ivy.`de.tototec::de.tobiasroeser.mill.integrationtest::0.6.1`
import $ivy.`io.chris-kipp::mill-ci-release::0.1.4`

import mill._
import mill.scalalib._
import io.kipp.mill.giter8.G8Module

object g8 extends G8Module {
  override def validationTargets =
    Seq("plugin.compile", "itest", "plugin.fix", "plugin.reformat")
}

/** Just a placeholder module to ensure that Scala Steward detects these and
  * then updates them in the actual template.
  */
object Steward extends ScalaModule {
  def scalaVersion = "2.13.8"
  def ivyDeps = Agg(
    ivy"org.scalameta::munit::0.7.29"
  )
}
