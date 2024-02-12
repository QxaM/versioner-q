package scanner.folder

import java.util.logging.Logger

class GradleFileLocation implements FolderScanner {

    private final static def GROOVY_FILENAME = 'build.gradle'
    private final static def LOGGER = Logger.getLogger('Gradle File Location Builder')

    private final def verbose

    GradleFileLocation(verbose) {
        this.verbose = verbose
    }

    @Override
    String findFileLocation(String folderLocation) {
        def fileLocation = folderLocation + '/' + GROOVY_FILENAME
        if (verbose) {
            LOGGER.info('Found build.gradle file location: ' + fileLocation)
        }
        return fileLocation
    }
}
