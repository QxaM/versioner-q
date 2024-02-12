package version

class Version {

    def static final VERSION = '0.3.0.1'

    static printVersion() {
        printVersionQ()
        printGroovyVersion()
    }

    static printVersionQ() {
        println 'Versioner-Q: ' + VERSION
    }

    static printGroovyVersion() {
        println 'Groovy: ' + GroovySystem.getVersion()
    }
}
