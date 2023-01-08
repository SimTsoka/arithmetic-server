package UnitTests.ServerTests.CommandsTests;

import Server.Commands.ShutdownCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShutdownCommandTests {

    @Test
    void initialiseShutdownCommand() {
        assertEquals("shutdown", new ShutdownCommand().getName());
    }
}
