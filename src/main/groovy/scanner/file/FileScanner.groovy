package scanner.file

trait FileScanner {

    final def verbose
    final def versionType
    final def fileLocation

    abstract String findVersion()
    abstract void bumpVersion()
}