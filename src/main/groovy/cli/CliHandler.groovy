package cli

import groovy.cli.commons.CliBuilder
import version.Version

import java.util.logging.Logger

class CliHandler {

    def final static FORMAT = ' %-15s%s\n'

    def final static LOGGER = Logger.getLogger('CliHandler')

    def cli
    def options
    def args
    def verbose

    CliHandler(args) {
        this.args = args
        this.cli = new CliBuilder(usage: 'versioner-q -[options] <versionType>')
        this.verbose = false

        cli.with {
            h longOpt: 'help', 'Show usage information'
            v longOpt: 'version', 'Show software version'
            _ longOpt: 'verbose', 'Show more output'
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

        if (options.'verbose') {
            verbose = true
            LOGGER.info('Logging more data from now on')
        }

        if (options.h) {
            cli.usage()
            cli.versionTypes()
        }

        if (options.v) {
            Version.printVersion()
        }
    }
}
