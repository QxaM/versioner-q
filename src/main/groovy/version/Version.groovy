package version

class Version {

    def static final VERSION = '0.4.0.0'

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
