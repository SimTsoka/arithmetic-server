package AcceptanceTests;

import Client.TestClient;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
                "arg", "[\"Simon\"]"
        ));
        JSONObject response = testClient.sendRequest(request);
        assertEquals("OK", response.get("result"));
        assertEquals("You have successfully launched into the program.\n", response.get("message"));
        assertEquals("Simon", response.get("name"));
    }
}
