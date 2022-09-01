package ServerTests;

import Server.ClientHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientHandlerTests {
    @Test
    void testIntro() {
        String expected = "Welcome to Arithmetics!!!\n" +
                        "To get started, please enter \"start\" followed by your name.\n";

        assertEquals(expected, ClientHandler.intro().getString("message"));
    }
}
