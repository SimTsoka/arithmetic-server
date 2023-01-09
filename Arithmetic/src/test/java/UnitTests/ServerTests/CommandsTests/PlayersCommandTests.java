package UnitTests.ServerTests.CommandsTests;

import Server.Commands.PlayersCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersCommandTests {
    @Test
    void initialisePlayersCommand() {
        assertEquals("players", new PlayersCommand().getName());
    }
}
