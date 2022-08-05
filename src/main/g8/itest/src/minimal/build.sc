import mill._, scalalib._
import \$exec.plugins
import $package$.Main 
import mill.eval.Evaluator
import \$ivy.`org.scalameta::munit:$munit$`
import munit.Assertions._

object minimal extends ScalaModule {
  def scalaVersion = "3.1.3"

  object test extends Tests with TestModule.Munit {
    def ivyDeps = Agg(ivy"org.scalameta::munit:$munit$")
  }
}

object other extends ScalaModule {
  def scalaVersion = "2.13.8"
}

def verify(ev: Evaluator) = T.command {
  val moduleCount = Main.countModules(ev)()
  assertEquals(moduleCount, 3)
}
