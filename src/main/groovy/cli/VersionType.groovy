package cli

enum VersionType {

    MAJOR('major'),
    MINOR('minor'),
    BUGFIX('bugfix')

    final def versionType

    VersionType(versionType) {
        this.versionType = versionType
    }
}