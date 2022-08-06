# An opinionated [Giter8][g8] template for For [Mill plugins][mill].

This template is meant to get you up and running quickly when wanting to make a
Mill plugin, test it, have CI for it (on GitHub), and release it (Maven
Central). It's basically the setup I personally use and got annoyed creating it
from scratch each time. This template includes the following:

- [mill-scalafix][mill-scalafix] for running [Scalafix][scalafix] rules like
    organize imports against your code.
- [Scalafmt module][scalafmt-module] configured to be able to format your code.
- [Publish module][publish-module] configured to be able to publish your plugin.
- [mill-vcs-version][mill-vcs-version] configured to set your version based on git tags.
- [mill-integrationtest][mill-integrationtest] configured to start testing your plugin right away.
- [GitHub workflow][github-workflows] set up for checking formatting, linting,
    testing, and publishing.
- [Release Drafer][release-drafter] set up to help with release notes.
- [Dependabot][dependabot] set up to send in prs to keep your actions up to date.
- [mill-dependency-submission][mill-dependency-submission] set up to submit your
    dependency tree to GitHub in order to get alerts about vulnerabilities.

**NOTE**: By default this template sets your license as [Apache 2.0][apache-2].
If you don't want this you'll need to update your LICENSE file.

## Using the template

You can use this template to generate a new project with [g8][g8] or with
[Mill][mill].

_Usage with g8_

```sh
g8 ckipp01/mill-plugin.g8
```

_Usage with Mill_
```sh
mill -i init ckipp01/mill-plugin.g8
```

Once your've created your project you should be able to `cd` into it and get
started! The project is already set up to have a working integration test
executable with `mill itest` to give you an example.

**NOTE**: Because this is using `mill-vcs-version` it expects your workspace to
be a git repository. You'll need to do a `git init` _prior_ to running `mill
itest` or you'll see a failure about your workspace not being a git repository.

## Publishing your plugin

When you're ready to publish your plugin the GitHub workflow is already present
in the `ci.yml`. However, it expects a few things to be set up before
publishing. It will need the following 4 [secrets][github-secrets] added to your repository:

- `PGP_PRIVATE_KEY` (base64 encoded)
- `PGP_PASSWORD`
- `SONATYPE_USER`
- `SONATYPE_PASWORD`

If you're unfamiliar with publishing process to Maven Central you can read a bit
more about it in the [Mill docs][publish-module] and also here on the [Scala 
Website][scala-publish] which explains a bit more about how to get an account
set up and keys created. Note that the examples use sbt, so ignore the
build-tool specific stuff and focus on the steps up to that point.

**NOTE**: The template sets up releasaing to `https://s01.oss.sonatype.org`
since as of Feb 2021 all new projects are provisioned there. If you have an
older account you can remove the added `--sonatypeUri` and
`--sonatypeSnapshotUri` in the `ci.yml` `publish-sonatype` step to use the
legacy host.

[g8]: http://www.foundweekends.org/giter8/
[mill]: https://com-lihaoyi.github.io/mill/mill/Intro_to_Mill.html
[scalafix]: https://scalacenter.github.io/scalafix/
[mill-scalafix]: https://github.com/joan38/mill-scalafix
[scalafmt-module]: https://com-lihaoyi.github.io/mill/mill/Configuring_Mill.html#_reformatting_your_code
[publish-module]: https://com-lihaoyi.github.io/mill/mill/Common_Project_Layouts.html#_publishing
[mill-vcs-version]: https://github.com/lefou/mill-vcs-version
[mill-integrationtest]: https://github.com/lefou/mill-integrationtest
[apache-2]: https://choosealicense.com/licenses/apache-2.0/
[github-workflows]: https://docs.github.com/en/actions/using-workflows
[release-drafter]: https://github.com/release-drafter/release-drafter
[dependabot]: https://github.com/dependabot
[mill-dependency-submission]: https://github.com/ckipp01/mill-dependency-submission
[github-secrets]: https://docs.github.com/en/codespaces/managing-codespaces-for-your-organization/managing-encrypted-secrets-for-your-repository-and-organization-for-github-codespaces
[scala-publish]: https://docs.scala-lang.org/overviews/contributors/index.html#publish-a-release
