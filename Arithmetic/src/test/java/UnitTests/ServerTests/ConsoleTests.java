package UnitTests.ServerTests;

import Players.PlayerDatabase;
import Server.Console;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleTests {

    private Console console;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        PlayerDatabase.reset();
        console = new Console();
        System.setOut(new PrintStream(outputStream));

    }

    @AfterEach
    void tearDown() {
        console = null;
        System.setOut(System.out);
    }

    @AfterAll
    static void clearDatabase() {
        PlayerDatabase.reset();
    }

    @Test
    void testShutdown() {
        console.initialise(generateInputStream("shutdown\n"));
        console.run();
        assertEquals("Shutting Down..", outputStream.toString().trim());
    }

    @Disabled
    @Test
    void testShutdownIncorrect() {
        console.initialise(generateInputStream("shutd0wn\nshutdown\n"));
        console.run();

        String expected = "Error! Please enter a valid server command.\nShutting Down..";
        assertEquals(expected, outputStream.toString().trim());
    }

    private InputStream generateInputStream(String userInput) {
        byte[] inputStreamData = userInput.getBytes();
        return new ByteArrayInputStream(inputStreamData);
    }
}
