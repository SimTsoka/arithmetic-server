package UnitTests.ServerTests;

import Players.PlayerDatabase;
import Server.ClientHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientHandlerTests {
    @BeforeEach
    void setUp() {
        ClientHandler.reset();
        PlayerDatabase.reset();
    }

    @Test
    void testIntro() {
        String expected = "Welcome to Arithmetics!!!\n" +
                        "To get started, please enter \"start\" followed by your username.\n";

        assertEquals(expected, ClientHandler.intro().getString("message"));
    }

    @Test
    void testCheckStartRequirements() {
        JSONObject request = new JSONObject();
        JSONArray args = new JSONArray(List.of("Simon"));
        request.put("command", "start");
        request.put("arg", args);

        assertEquals("OK", ClientHandler.checkStartRequirements(request).getString("status"));
        assertEquals("You have successfully launched into the program.\n",
                ClientHandler.checkStartRequirements(request).getString("message"));
        assertEquals("Simon", ClientHandler.checkStartRequirements(request).getString("name"));

        assertEquals("Simon", ClientHandler.getPlayer().getName());
        assertTrue(ClientHandler.isPlayerCreated());
    }

    @Test
    void testCheckStartRequirementsFailed() {
        JSONObject request = new JSONObject();
        JSONArray args = new JSONArray(List.of("$imon"));
        request.put("command", "start");
        request.put("arg", args);
        boolean nameExists = true;

        JSONObject actual = ClientHandler.checkStartRequirements(request);
        assertEquals("ERROR", actual.getString("status"));
        assertEquals("Please enter \"start\" followed by your username.\n", actual.getString("message"));

        try {
            actual.getString("name");
        } catch (JSONException e) {
            nameExists = false;
        }

        assertFalse(nameExists);
        assertNull(ClientHandler.getPlayer());
        assertFalse(ClientHandler.isPlayerCreated());
    }
}
