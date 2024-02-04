package cli

import groovy.cli.commons.CliBuilder
import version.Version

class CliHandler {

    def final static FORMAT = ' %-15s%s\n'

    def cli
    def options
    def args

    CliHandler(args) {
        this.args = args
        this.cli = new CliBuilder(usage: 'versioner-q -[options] <versionType>')

        cli.with {
            h longOpt: 'help', 'Show usage information'
            v longOpt: 'version', 'Show software version'
            g longOpt: 'gradle', 'Update Gradle project version'
        }

        cli.metaClass.versionTypes() {
            println '\nPossible version types: '
            printf FORMAT, 'major', 'Increments major version number'
            printf FORMAT, 'minor', 'Increments minor version number'
            printf FORMAT, 'bugfix', 'Increments bugfix version number'
        }
    }

    void handleCli() {
        options = cli.parse(args)

        if (options.h) {
            cli.usage()
            cli.versionTypes()
        }

        if (options.v) {
            Version.printVersion()
        }
    }
}
