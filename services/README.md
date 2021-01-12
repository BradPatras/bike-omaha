# service-builder

`service-builder` is a command line tool written in swift that facilitates building the static json files that will be served to the app.

### How to run
Firstly build the swift package:
```
// from within the services directory
swift build
```
Then to run the compiled tool:
```
// from within the services directory
swift run service-builder build <path/to/metadata.json> <path/to/sources/dir> <path/to/output.json>
// the <path/to/output.json> argument is optional and will default to `output.json` in the services directory
```

Swift argument-parser auto generates help pages, so you can always run `swift run service-builder -h` for more info

### Links and stuff

[following this tutorial for getting the basics set up](https://www.avanderlee.com/swift/command-line-tool-package-manager/)
and also utilizing swift's [argument-parser](https://swift.org/blog/argument-parser/) library


