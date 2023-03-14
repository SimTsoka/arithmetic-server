package AcceptanceTests;

import Client.TestClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStartCommand {
    private final static int PORT = 5000;
    private final static String IP = "localhost";
    private final TestClient testClient = new TestClient();

    @BeforeEach
    void connectToServer() {
        testClient.connect(IP, PORT);
    }

    @AfterEach
    void disconnectFromServer() {
        testClient.disconnect();
    }

    @Test
    void testStartSuccessful() {
        JSONObject request = new JSONObject(Map.of(
                "command", "start",
                "arg", new JSONArray(List.of("Simon"))
        ));
        checkIntroMsg();

        JSONObject response = testClient.sendRequest(request);
        assertEquals("OK", response.get("status"));
        assertEquals("You have successfully launched into the program.\n", response.get("message"));
        assertEquals("Simon", response.get("name"));
    }

    @Test
    void testStartUnsuccessfulWrongName() {
        JSONObject request = new JSONObject(Map.of(
                "command", "start",
                "arg", new JSONArray(List.of("S!mon"))
        ));

        checkIntroMsg();
        JSONObject response = testClient.sendRequest(request);
        assertEquals("ERROR", response.get("status"));
        assertEquals("Please enter \"start\" followed by your username.\n", response.get("message"));
    }

    @Test
    void testStartUnsuccessfulIncorrectSpelling() {
        JSONObject request = new JSONObject(Map.of(
                "command", "st@rt",
                "arg", new JSONArray(List.of("Simon"))
        ));

        checkIntroMsg();
        JSONObject response = testClient.sendRequest(request);
        assertEquals("ERROR", response.get("status"));
        assertEquals("Please enter \"start\" followed by your username.\n", response.get("message"));
    }

    void checkIntroMsg() {
        String introMsg = "Welcome to Arithmetics!!!\n" +
                "To get started, please enter \"start\" followed by your username.\n";

        JSONObject introResponse = testClient.receiveResponse();
        assertEquals(introMsg, introResponse.getString("message"));
    }
}
