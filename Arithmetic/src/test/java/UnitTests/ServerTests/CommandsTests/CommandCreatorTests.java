package UnitTests.ServerTests.CommandsTests;

import Server.Commands.PlayersCommand;
import Server.Commands.ServerCommand;
import Server.Commands.ShutdownCommand;
import Server.Commands.Validation.HelpCommand;
import Server.Commands.WrongCommand;
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

    @Test
    void testCreateWrongCommand() {
        assertEquals(new WrongCommand(), ServerCommand.createCommand("shutd0wn"));
    }

    @Test
    void testCreateHelpCommand() {
        assertEquals(new HelpCommand(), ServerCommand.createCommand("Help"));
    }
}
