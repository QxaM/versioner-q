package cli

import groovy.cli.commons.CliBuilder
import version.Version

class CliHandler {

    def cli
    def options
    def args

    CliHandler(args) {
        this.args = args
        this.cli = new CliBuilder(usage: 'versioner-q -[options]')

        cli.with {
            h longOpt: 'help', 'Show usage information'
            v longOpt: 'version', 'Show software version'
            g longOpt: 'gradle', 'Update Gradle project version'
        }
    }

    void handleCli() {
        options = cli.parse(args)

        if (options.h) {
            cli.usage()
        }

        if (options.v) {
            Version.printVersion()
        }
    }
}
