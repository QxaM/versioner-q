package cli

enum VersionType {

    MAJOR('major'),
    MINOR('minor'),
    BUGFIX('bugfix')

    def versionType

    VersionType(versionType) {
        this.versionType = versionType
    }
}