package UnitTests.ServerTests;

import Server.ClientHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientHandlerTests {
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

        assertEquals("OK", ClientHandler.checkStartRequirements(request).getString("result"));
        assertEquals("You have successfully launched into the program.\n",
                ClientHandler.checkStartRequirements(request).getString("message"));
        assertEquals("Simon", ClientHandler.checkStartRequirements(request).getString("name"));
    }

    //Test unsuccessful launch and make sure an actual robot is created
}
