package scanner.folder

import groovy.test.GroovyTestCase

class GroovyFileLocationTests extends GroovyTestCase {

    void testReturnFileLocation() {
        // Given
        def location = 'C:/users/project'
        GradleFileLocation gradleFileLocation = new GradleFileLocation(true)

        // When
        def fileLocation = gradleFileLocation.findFileLocation(location)

        // Then
        assert 'C:/users/project/build.gradle' == fileLocation
    }
}
