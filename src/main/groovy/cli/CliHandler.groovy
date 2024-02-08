package cli

import groovy.cli.commons.CliBuilder
import version.Version

import java.util.logging.Logger

class CliHandler {

    def final static FORMAT = ' %-15s%s\n'
    def final static FILE_REGEX = /.*(\\|\/).*/

    def final static LOGGER = Logger.getLogger('CliHandler')

    def cli
    def options
    def args
    def verbose
    def extraArguments

    def projectType
    def folderToScan
    def versionType

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
            printf FORMAT, VersionType.MAJOR.versionType, 'Increments major version number'
            printf FORMAT, VersionType.MINOR.versionType, 'Increments minor version number'
            printf FORMAT, VersionType.BUGFIX.versionType, 'Increments bugfix version number'
        }
    }

    void handleCli() throws IncorrectLocationsProvided, IncorrectArgumentsNumber {
        options = cli.parse(args)
        extraArguments = options.arguments().flatten().findAll{ it != null }

        handleVerbose()
        printProvidedArguments()

        if (extraArguments.size() != 2) {
            throw new IncorrectArgumentsNumber('Provided incorrect number of arguments')
        }

        handleFolderToScan()
        handleVersionType()

        handleHelp()
        handleVersion()
    }

    void handleVerbose() {
        if (options.'verbose') {
            verbose = true
            LOGGER.info('Logging more data from now on')
        }
    }

    void printProvidedArguments() {
        if (verbose) {
            LOGGER.info('Arguments size: ' + extraArguments.size())
            LOGGER.info('Provided arguments: ' + extraArguments)
        }
    }

    void handleFolderToScan() throws IncorrectLocationsProvided {
        def fileFolder = extraArguments.findAll {
            it =~ FILE_REGEX
        }

        if (fileFolder.size() == 1) {
            folderToScan = fileFolder.get(0)
        } else {
            throw new IncorrectLocationsProvided("Provided none or too many locations to scan by " +
                    "the versioner")
        }

        if (verbose) {
            LOGGER.info('Found fileFolder(s): ' + fileFolder)
        }
    }

    void handleVersionType() {
        def versionTypes = VersionType.values().versionType

        if (verbose) {
            println 'Available version types: ' + versionTypes
        }

        def versions = extraArguments.intersect(versionTypes)
        if (verbose) {
            println 'Found version types in arguments: ' + versions
        }

        if (versions.size() != 1) {
            throw new IncorrectVersionNumber('Provided incorrect number of version types')
        }
        versionType = versions.get(0)
    }

    void handleHelp() {
        if (options.h) {
            cli.usage()
            cli.versionTypes()
        }
    }

    void handleVersion() {
        if (options.v) {
            Version.printVersion()
        }
    }
}
