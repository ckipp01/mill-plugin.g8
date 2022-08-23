package $package$

import mill._
import mill.define.ExternalModule
import mill.eval.Evaluator
import mill.main.EvaluatorScopt
import mill.scalalib.JavaModule

object Main extends ExternalModule {
  def countModules(ev: Evaluator) = T.command {
    val log = T.ctx().log
    log.info("Let's count how many modules you have!")

    val modules = computeModules(ev)

    modules.foreach { module =>
      log.info(s"Found module \${module.artifactName}!}")
    }
    modules.size
  }

  private def computeModules(ev: Evaluator) =
    ev.rootModule.millInternal.modules.collect { case j: JavaModule => j }

  implicit def millScoptEvaluatorReads[T]: EvaluatorScopt[T] =
    new mill.main.EvaluatorScopt[T]()

  lazy val millDiscover = mill.define.Discover[this.type]
}
