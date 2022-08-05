import mill._
import mill.scalalib._
import mill.scalalib.scalafmt._
import mill.scalalib.publish._
import mill.scalalib.api.ZincWorkerUtil
import \$ivy.`com.goyeau::mill-scalafix::$mill-scalafix$`
import com.goyeau.mill.scalafix.ScalafixModule
import mill.scalalib.api.Util.scalaNativeBinaryVersion
import \$ivy.`de.tototec::de.tobiasroeser.mill.integrationtest::$mill-integrationtest$`
import de.tobiasroeser.mill.integrationtest._
import \$ivy.`de.tototec::de.tobiasroeser.mill.vcs.version::$mill-vcs$`
import de.tobiasroeser.mill.vcs.version.VcsVersion
import scala.util.Try

val millVersion = "0.10.3"
val scala213 = "$scala-version$"
val pluginName = "$name$"

def millBinaryVersion(millVersion: String) = scalaNativeBinaryVersion(
  millVersion
)

object plugin
    extends ScalaModule
    with PublishModule
    with ScalafixModule
    with ScalafmtModule {

  override def scalaVersion = scala213

  override def publishVersion = VcsVersion
    .vcsState()
    .format(
      tagModifier = {
        case t if t.startsWith("v") && Try(t.substring(1, 2).toInt).isSuccess =>
          t.substring(1)
        case t => t
      },
      noTagFallback = "0.1.0"
    )

  override def pomSettings = PomSettings(
    description = "$description$",
    organization = "$organization$",
    url = "https://github.com/$github-handle$/$name$",
    licenses = Seq(License.`Apache-2.0`),
    versionControl = VersionControl
      .github(owner = "$github-handle$", repo = "$name$"),
    developers =
      Seq(Developer("$github-handle$", "$developer-name$", "https://github.com/$github-handle$"))
  )

  override def compileIvyDeps = super.compileIvyDeps() ++ Agg(
    ivy"com.lihaoyi::mill-scalalib:\${millVersion}"
  )

  override def scalacOptions = Seq("-Ywarn-unused", "-deprecation")

  override def scalafixScalaBinaryVersion = ZincWorkerUtil.scalaBinaryVersion(scala213)

  override def scalafixIvyDeps = Agg(ivy"com.github.liancheng::organize-imports:$organize-imports$")
}

object itest extends MillIntegrationTestModule {

  override def millTestVersion = millVersion

  override def pluginsUnderTest = Seq(plugin)

  def testBase = millSourcePath / "src"

  override def testInvocations: T[Seq[(PathRef, Seq[TestInvocation.Targets])]] =
    T {
      Seq(
        PathRef(testBase / "minimal") -> Seq(
          TestInvocation.Targets(Seq("verify"), noServer = true)
        )
      )
    }
}
