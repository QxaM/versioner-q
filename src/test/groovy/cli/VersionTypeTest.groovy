package cli

import groovy.test.GroovyTestCase

class VersionTypeTest extends GroovyTestCase {

    void testGetMajor() {
        // Given + When
        def major = VersionType.MAJOR.versionType
        // Then
        assert major == 'major'
    }

    void testGetMinor() {
        // Given + When
        def minor = VersionType.MINOR.versionType
        // Then
        assert minor == 'minor'
    }

    void testGetBugfix() {
        // Given + When
        def bugfix = VersionType.BUGFIX.versionType
        //Then
        assert bugfix == 'bugfix'
    }
}
