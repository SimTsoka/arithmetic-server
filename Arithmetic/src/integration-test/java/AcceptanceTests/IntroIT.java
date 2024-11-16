package AcceptanceTests;

import Client.TestClient;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntroIT {
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
    void testIntroMsg() {
        String introMsg = "Welcome to Arithmetics!!!\n" +
                "To get started, please enter \"start\" followed by your username.\n";

        JSONObject response = testClient.receiveResponse();
        assertEquals(introMsg, response.getString("message"));
    }
}
