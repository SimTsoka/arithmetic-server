package UnitTests.ServerTests;

import Players.PlayerDatabase;
import Server.Console;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        byte[] inputStreamData = "shutdown\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(inputStreamData);

        console.initialise(inputStream);
        console.run();
        assertEquals("Shutting Down..", outputStream.toString().trim());
    }
}
