package UnitTests.ServerTests.CommandsTests;

import Server.Commands.PlayersCommand;
import Server.Commands.ServerCommand;
import Server.Commands.ShutdownCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandCreatorTests {

    @Test
    void testCreateShutdownCommand() {
        assertEquals(new ShutdownCommand(), ServerCommand.createCommand("Shutdown"));
    }

    @Test
    void testCreatePlayersCommand() {
        assertEquals(new PlayersCommand(), ServerCommand.createCommand("Players"));
    }
}
