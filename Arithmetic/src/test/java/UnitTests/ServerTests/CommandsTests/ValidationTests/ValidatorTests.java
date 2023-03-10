package UnitTests.ServerTests.CommandsTests.ValidationTests;

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
        assertEquals("", new CommandValidator("   ").validate());
        assertEquals("Error! Please enter a command.", getOutputStream());
    }

    @Test
    void singleCommandSuccessful() {
        assertEquals("players", new CommandValidator("PLayers").validate());
        assertEquals("shutdown", new CommandValidator("SHUtdown").validate());
        assertEquals("help", new CommandValidator("HELP").validate());
    }

    @Test
    void singleCommandUnsuccessful() {
        assertEquals("", new CommandValidator("PL@yers").validate());
        assertEquals("Invalid Command! Enter \"help\" for a list of valid commands.", getOutputStream());
    }

    @Test
    void moreThanOneWordEntered() {
        assertEquals("", new CommandValidator("help me").validate());
        assertEquals("Invalid Command! Enter \"help\" for a list of valid commands.", getOutputStream());
    }

    String getOutputStream() {
        return outputStream.toString().trim();
    }
}
