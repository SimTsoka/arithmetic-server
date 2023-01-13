package UnitTests.ServerTests.CommandsTests;

import Server.Commands.ShutdownCommand;
import Server.Commands.WrongCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class WrongCommandTests {
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
    void testExecute() {
        assertTrue(new WrongCommand().execute());
        assertEquals("Error! Please enter a valid server command.", outputStream.toString().trim());
    }
}
