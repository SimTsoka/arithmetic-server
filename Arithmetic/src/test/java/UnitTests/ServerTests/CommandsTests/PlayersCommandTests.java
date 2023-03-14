package UnitTests.ServerTests.CommandsTests;

import Players.Player;
import Players.PlayerDatabase;
import Server.ServerCommands.PlayersCommand;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayersCommandTests {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        PlayerDatabase.reset();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @AfterAll
    static void afterEveryTest() {
        PlayerDatabase.reset();
    }

    @Test
    void initialisePlayersCommand() {
        assertEquals("players", new PlayersCommand().getName());
    }

    @Test
    void testExecuteWithNoPlayers() {
        assertTrue(new PlayersCommand().execute());
        assertEquals("No players found.", outputStream.toString().trim());
    }

    @Test
    void testExecuteWithPlayers() {
        PlayerDatabase.addNewPlayer(new Player("Simon"));
        PlayerDatabase.addNewPlayer(new Player("Jane"));

        assertTrue(new PlayersCommand().execute());
        assertEquals("Number of players = 2\n" +
                "Players:\nJane\nSimon",outputStream.toString().trim());
    }
}
