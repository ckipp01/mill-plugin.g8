import mill._, scalalib._
import \$exec.plugins
import $package$.Main 
import mill.eval.Evaluator
import \$ivy.`org.scalameta::munit:0.7.29`
import munit.Assertions._

object minimal extends ScalaModule {
  def scalaVersion = "3.1.3"

  object test extends Tests with TestModule.Munit {
    def ivyDeps = Agg(ivy"org.scalameta::munit:0.7.29")
  }
}

object other extends ScalaModule {
  def scalaVersion = "2.13.11"
}

def verify(ev: Evaluator) = T.command {
  val moduleCount = Main.countModules(ev)()
  assertEquals(moduleCount, 3)
}
