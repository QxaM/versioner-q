#!/usr/bin/env groovy
import groovy.cli.commons.CliBuilder
import version.Version

def cli = new CliBuilder(usage: 'versioner-q -[options]')

cli.with {
    h longOpt: 'help', 'Show usage information'
    v longOpt: 'version', 'Show software version'
    g longOpt: 'gradle', 'Update Gradle project version'
}

def options = cli.parse(args)

if (options.h) {
    cli.usage()
}

if (options.v) {
    Version.printVersion()
}

