#!/usr/bin/env groovy
import cli.CliHandler
import cli.IncorrectLocationsProvided

import java.util.logging.Logger


def final LOGGER = Logger.getLogger('CliHandler')

println 'Versioner-Q'

CliHandler cliHandler = new CliHandler(args)

try {
    cliHandler.handleCli()
} catch (IncorrectLocationsProvided e) {
    LOGGER.severe(e.getMessage())
    println 'Provided too many or too little locations for versioner too scan'
    println 'Do not provide location to scan - run versioner from folder to scan'
}