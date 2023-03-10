package UnitTests.ServerTests;

import Players.Player;
import Players.PlayerDatabase;
import Server.Console;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsoleTests {

    private Console console;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    static void clearDatabase() {
        PlayerDatabase.reset();
    }
    @BeforeEach
    void setUp() {
        console = new Console();
        System.setOut(new PrintStream(outputStream));

    }

    @AfterEach
    void tearDown() {
        console = null;
        System.setOut(System.out);
        PlayerDatabase.reset();
    }

    @Test
    void testShutdown() {
        console.initialise(generateInputStream("shutdown\n"));
        console.run();
        assertEquals("Shutting Down..", outputStream.toString().trim());
    }

//    @Disabled
    @Test
    void testShutdownIncorrect() {
        console.initialise(generateInputStream("shutd0wn\nshutdown"));
        console.run();

        String expected = "Invalid Command! Enter \"help\" for a list of valid commands.\nShutting Down..";
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void testPlayersCommandWithNoPlayers() {
        console.initialise(generateInputStream("Players\nshutdown\n"));
        console.run();

        String expected = "No players found.\nShutting Down..";
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void testPlayersCommandWithPlayers() {
        generateAndAddPlayers(2);

        console.initialise(generateInputStream("Players\nshutdown\n"));
        console.run();

        String expected = """
                Number of players = 2
                Players:
                Player_1
                Player_2
                Shutting Down..""";
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void testHelp() {
        console.initialise(generateInputStream("Help\nshutdown"));
        console.run();

        String expected = """
                HERE IS A LIST OF COMMANDS:
                Help - For more information on commands.
                Players - Returns a list of players that have launched into the program.
                Shutdown - Shuts the whole server down.
                Shutting Down..""";
        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void testEmptyInput() {
        console.initialise(generateInputStream("\nshutdown"));
        console.run();

        String expected = """
                Error! Please enter a valid server command.
                Shutting Down..""";

        assertEquals(expected, outputStream.toString().trim());
    }

    @Test
    void testInvalidInput() {
        console.initialise(generateInputStream("help me\nhelp\nshutdown"));
        console.run();

        String expected = """
                Invalid Command! Enter "help" for a list of valid commands.
                HERE IS A LIST OF COMMANDS:
                Help - For more information on commands.
                Players - Returns a list of players that have launched into the program.
                Shutdown - Shuts the whole server down.
                Shutting Down..""";

        assertEquals(expected, outputStream.toString().trim());
    }

    private InputStream generateInputStream(String userInput) {
        byte[] inputStreamData = userInput.getBytes();
        return new ByteArrayInputStream(inputStreamData);
    }

    private void generateAndAddPlayers(int n) {
        for (int i = 0; i < n; i++) {
            PlayerDatabase.addNewPlayer(new Player("Player_"+(i+1)));
        }
    }
}
