package AcceptanceTests.SupportingCode;

import Client.TestClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaunchSetup {

    private final static int PORT = 5000;
    private final static String IP = "localhost";

    void launchNClients(int n){
        for (int i = 0; i < n; i++) {
            launchClient("Player"+i+1);
        }
    }

    void launchClient(String name) {
        JSONObject request = createRequestMessage(name);
        TestClient client = createClient();
        client.connect(IP, PORT);
        checkIntroMsg(client);
        checkIfLaunchSuccessful(client, request, name);
        client.disconnect();
    }

    TestClient createClient(){
        return new TestClient();
    }

    void checkIfLaunchSuccessful(TestClient client, JSONObject request, String name) {
        JSONObject response = client.sendRequest(request);
        assertEquals("OK", response.get("status"));
        assertEquals("You have successfully launched into the program.\n", response.get("message"));
        assertEquals(name, response.get("name"));
    }

    JSONObject createRequestMessage(String name) {
        return new JSONObject(Map.of(
                "command", "start",
                "arg", new JSONArray(List.of(name))
        ));
    }

    void checkIntroMsg(TestClient client) {
        String introMsg = "Welcome to Arithmetics!!!\n" +
                "To get started, please enter \"start\" followed by your username.\n";

        JSONObject introResponse = client.receiveResponse();
        assertEquals(introMsg, introResponse.getString("message"));
    }
}
