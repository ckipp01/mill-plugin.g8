import \$ivy.`com.goyeau::mill-scalafix::0.2.11`
import \$ivy.`de.tototec::de.tobiasroeser.mill.integrationtest::0.6.1`
import \$ivy.`io.chris-kipp::mill-ci-release::0.1.5`

import mill._
import mill.scalalib._
import mill.scalalib.scalafmt._
import mill.scalalib.publish._
import mill.scalalib.api.ZincWorkerUtil
import com.goyeau.mill.scalafix.ScalafixModule
import mill.scalalib.api.Util.scalaNativeBinaryVersion
import de.tobiasroeser.mill.integrationtest._
import io.kipp.mill.ci.release.CiReleaseModule

val millVersion = "0.10.0"
val scala213 = "2.13.10"
val pluginName = "$name$"

def millBinaryVersion(millVersion: String) = scalaNativeBinaryVersion(
  millVersion
)

object plugin
    extends ScalaModule
    with CiReleaseModule 
    with ScalafixModule
    with ScalafmtModule {

  override def scalaVersion = scala213

  override def artifactName =
    s"\${pluginName}_mill\${millBinaryVersion(millVersion)}"

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

  override def scalafixIvyDeps = Agg(ivy"com.github.liancheng::organize-imports:0.6.0")
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
