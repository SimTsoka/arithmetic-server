package AcceptanceTests.SupportingCode;

import Client.TestClient;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLaunchingMultipleClientSupport {
    private TestClient client;

    public void createClient() {
        client = new TestClient();
    }

    public void connect() {
        client.connect("localhost", 5000);
    }

    public void disconnect() {
        client.disconnect();
    }

    public boolean launchClient(String name) {
        JSONObject request = TestStartSupportingCode.createRequestMessage(name);

        TestStartSupportingCode.checkIntroMsg(client);
        JSONObject response = client.sendRequest(request);

        return response.get("message").equals("You have successfully launched into the program.\n");
    }
}
