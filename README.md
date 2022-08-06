# An opinionated [Giter8][g8] template for For [Mill plugins][mill].

This template is meant to get you up and running quickly when wanting to make a
Mill plugin, test it, have CI for it (on GitHub), and release it (Maven
Central). This template includes the following:

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
