package cli

import groovy.test.GroovyTestCase

import static org.junit.jupiter.api.Assertions.assertThrows

class CliHandlerTest extends GroovyTestCase {

    private final static def LOCATION = 'C:/users/project'

    void testShouldInitialise() {
        // Given
        def args = ['-h', LOCATION]

        // When
        CliHandler cliHandler = new CliHandler(args)

        // Then
        assertEquals(args, cliHandler.args)
        assertNotNull(cliHandler.cli)
        assertNotNull(cliHandler.options)
        assertNotNull(cliHandler.extraArguments)
        assertTrue(cliHandler.options.h)
        assertEquals(LOCATION, cliHandler.extraArguments.get(0))
        assertFalse(cliHandler.verbose)
    }

    void testShouldHandleVerbose() {
        // Given
        def args = ['--verbose', LOCATION]
        CliHandler cliHandler = new CliHandler(args)

        // When
        cliHandler.handleVerbose()

        // Then
        assertTrue(cliHandler.verbose)
    }

    void testShouldHandleFolders() {
        // Given
        def args = ['--verbose', LOCATION]
        CliHandler cliHandler = new CliHandler(args)

        // When
        cliHandler.handleFolderToScan()

        // Then
        assert LOCATION == cliHandler.folderToScan
    }

    void testShouldThrow_WhenNoLocationProvided() {
        // Given
        def args = ['--verbose']
        CliHandler cliHandler = new CliHandler(args)

        // When + Then
        assertThrows(IncorrectLocationsProvided.class,
                () -> cliHandler.handleFolderToScan())
    }

    void testShouldThrow_WhenTooManyLocationsProvided() {
        // Given
        def args = ['--verbose', LOCATION, LOCATION]
        CliHandler cliHandler = new CliHandler(args)

        // When + Then
        assertThrows(IncorrectLocationsProvided.class,
                () -> cliHandler.handleFolderToScan())
    }

    void testShouldHandleVersionType() {
        // Given
        def args = ['--verbose', LOCATION, 'major']
        CliHandler cliHandler = new CliHandler(args)

        // When
        cliHandler.handleVersionType()

        // Then
        assert 'major' == cliHandler.versionType
    }

    void testShouldThrow_WhenNoVersionTypeProvided() {
        // Given
        def args = ['--verbose', LOCATION]
        CliHandler cliHandler = new CliHandler(args)

        // When + Then
        assertThrows(IncorrectVersionNumber.class,
                () -> cliHandler.handleVersionType())
    }

    void testShouldThrow_WhenTooManyVersionTypesProvided() {
        // Given
        def args = ['--verbose', LOCATION, 'major', 'minor']
        CliHandler cliHandler = new CliHandler(args)

        // When + Then
        assertThrows(IncorrectVersionNumber.class,
                () -> cliHandler.handleVersionType())
    }

    void testShouldHandleCli() {
        // Given
        def args = ['--verbose', LOCATION, 'major']
        CliHandler cliHandler = new CliHandler(args)

        // When
        cliHandler.handleCli()

        // Then
        assert 'major' == cliHandler.versionType
        assert LOCATION == cliHandler.folderToScan
        assertTrue(cliHandler.verbose)
    }

    void testShouldThrow_WhenTooLittleArgumentsProvided() {
        // Given
        def args = ['--verbose', LOCATION]
        CliHandler cliHandler = new CliHandler(args)

        // When
        assertThrows(IncorrectArgumentsNumber.class, () -> cliHandler.handleCli())
    }

    void testShouldThrow_WhenTooManyArgumentsProvided() {
        // Given
        def args = ['--verbose', LOCATION, 'major', LOCATION]
        CliHandler cliHandler = new CliHandler(args)

        // When
        assertThrows(IncorrectArgumentsNumber.class, () -> cliHandler.handleCli())
    }
}
