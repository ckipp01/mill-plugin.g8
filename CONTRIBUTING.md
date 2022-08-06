# Contributing

Thanks for being willing to contribute! If you're unfamiliar with g8 templates
you'll want to check out the [docs][g8] first. By default Giter8 only comes with
an sbt plugin to test your template, so we have a bit of a hacky setup here to
ensure that it's able to be generated and also be able to run a few Mill
commands. You can do this with:

```sh
mill g8.test
```

This will generate your template in your dest directory and then run the
specified commands against your generated plugin.

If you want to test it manually with `g8` you can also do that with:

```sh
g8 file://path/to/your/repo
```

[g8]: http://www.foundweekends.org/giter8/template.html
