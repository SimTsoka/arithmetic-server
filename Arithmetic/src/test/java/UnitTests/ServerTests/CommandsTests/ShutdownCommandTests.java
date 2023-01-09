package UnitTests.ServerTests.CommandsTests;

import Players.PlayerDatabase;
import Server.Commands.ShutdownCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ShutdownCommandTests {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    // Create a class that creates outputStream also resets everything
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void initialiseShutdownCommand() {
        assertEquals("shutdown", new ShutdownCommand().getName());
    }

    @Test
    void testExecute() {
        assertFalse(new ShutdownCommand().execute());
        assertEquals("Shutting Down..", outputStream.toString().trim());
    }
}
