package UnitTests.ServerTests.CommandsTests;

import Server.ServerCommands.HelpCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class HelpCommandTests {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void initialiseHelpCommand() {
        assertEquals("help", new HelpCommand().getName());
    }

    @Test
    void testExecute() {
        assertTrue(new HelpCommand().execute());
        assertEquals(helpIndex(), outputStream.toString());
    }

    private String helpIndex() {
        return """
                HERE IS A LIST OF COMMANDS:
                Help - For more information on commands.
                Players - Returns a list of players that have launched into the program.
                Shutdown - Shuts the whole server down.
                """;
    }
}
