package AcceptanceTests;

import Client.TestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestConnection {
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
    void testConnection() {
        assertTrue(testClient.isConnected());
    }
}
