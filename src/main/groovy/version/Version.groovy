package version

class Version {

    def static final VERSION = '0.0.4.0'

    static printVersion() {
        printVersionQ()
        printGroovyVersion()
    }

    static printVersionQ() {
        println 'Version-Q: ' + VERSION
    }

    static printGroovyVersion() {
        println 'Groovy: ' + GroovySystem.getVersion()
    }
}
