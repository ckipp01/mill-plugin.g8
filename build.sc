import $ivy.`org.foundweekends.giter8::giter8-lib:0.14.0`

import java.io.FileInputStream

import scala.util.Using

import giter8._
import mill.api.Result

object g8 extends mill.Module {
  def test = T {
    val log = T.log
    val cwd = os.pwd
    val templateBase = cwd / "src" / "main" / "g8"

    val projectDest = T.dest / "tester"

    val propsFile = templateBase / "default.properties"
    val rawProps =
      Using(new FileInputStream(propsFile.toIO))(G8.readProps).toEither.left
        .map(_.getMessage())

    val result = for {
      raw <- rawProps
      props <- G8.transformProps(raw)
      result <- G8.fromDirectory(
        templateDirectory = os.pwd.toIO,
        workingDirectory = os.pwd.toIO,
        arguments = props.map { case (key, value) => s"--${key}=${value}" },
        forceOverwrite = true,
        outputDirectory = Some(projectDest.toIO)
      )
    } yield result

    result match {
      case Left(err) => Result.Failure(err)
      case Right(msg) =>
        log.info(msg)

        val commands =
          Seq("plugin.compile", "itest", "plugin.fix", "plugin.reformat")

        val results = commands.zipWithIndex.map { case (command, id) =>
          log.info(
            s"""[${id + 1}/${commands.size}] attempting to run "${command}""""
          )
          val cmd = os
            .proc("./mill", "--no-server", command)
            .call(cwd = projectDest)
          cmd.exitCode
        }

        if (results.forall(_ == 0)) {
          val msg = "Ran all commands successfully!"
          log.info(msg)
          Result.Success(msg)
        } else {
          Result.Failure(s"you got issues: [${results.mkString(" ")}]")
        }
    }
  }
}
