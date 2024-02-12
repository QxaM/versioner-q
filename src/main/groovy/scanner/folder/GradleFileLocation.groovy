package scanner.folder

class GradleFileLocation implements FolderScanner {

    private final static def GROOVY_FILENAME = 'build.gradle'

    @Override
    String findFileLocation(String folderLocation) {
        return folderLocation + '/' + GROOVY_FILENAME
    }
}
