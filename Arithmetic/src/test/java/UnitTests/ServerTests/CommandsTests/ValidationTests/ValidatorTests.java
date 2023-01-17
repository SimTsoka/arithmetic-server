package UnitTests.ServerTests.CommandsTests.ValidationTests;

import Players.PlayerDatabase;
import Server.Commands.Validation.CommandValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTests {

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
    void noCommandEntered() {
        assertEquals("", new CommandValidator("").validate());
        assertEquals("Error! Please enter a command.", outputStream.toString().trim());
    }

    @Test
    void singleCommandSuccessful() {
        assertEquals("players", new CommandValidator("PLayers").validate());
        assertEquals("shutdown", new CommandValidator("SHUtdown").validate());
    }
}
