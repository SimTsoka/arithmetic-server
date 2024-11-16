package UnitTests.ServerTests;

import Players.PlayerDatabase;
import Server.ClientHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientHandlerTests {
    private ClientHandler clientHandler;

    @BeforeEach
    void setUp() {
        clientHandler = new ClientHandler();
        PlayerDatabase.reset();
    }

    @AfterEach
    void tearDown() {
        clientHandler = null;
        PlayerDatabase.reset();
    }

    @Test
    void testIntro() {
        String expected = """
                Welcome to Arithmetics!!!
                To get started, please enter "start" followed by your username.
                """;

        assertEquals(expected, clientHandler.intro().getString("message"));
    }

    @Test
    void testCheckStartRequirements() {
        JSONObject request = new JSONObject();
        JSONObject response;
        JSONArray args = new JSONArray(List.of("Simon"));
        request.put("command", "start");
        request.put("arg", args);

        response = clientHandler.checkStartRequirements(request);

        assertEquals("OK", response.getString("status"));
        assertEquals("You have successfully launched into the program.\n",
                response.getString("message"));
        assertEquals("Simon", response.getString("name"));

        assertEquals("Simon", clientHandler.getPlayer().getName());
        assertTrue(clientHandler.isPlayerCreated());
    }

    @Test
    void testCheckStartRequirementsFailed() {
        JSONObject request = new JSONObject();
        JSONArray args = new JSONArray(List.of("$imon"));
        request.put("command", "start");
        request.put("arg", args);
        boolean nameExists = true;

        JSONObject actual = clientHandler.checkStartRequirements(request);
        assertEquals("ERROR", actual.getString("status"));
        assertEquals("Please enter \"start\" followed by your username.\n", actual.getString("message"));

        try {
            actual.getString("name");
        } catch (JSONException e) {
            nameExists = false;
        }

        assertFalse(nameExists);
        assertNull(clientHandler.getPlayer());
        assertFalse(clientHandler.isPlayerCreated());
    }
}
