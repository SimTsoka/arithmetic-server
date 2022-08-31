package Client;

import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TestClient {
    private Socket socket;
    private PrintStream out;
    private BufferedReader in;

    public void connect(String ipAddress, int port) {
        try {
            socket = new Socket(ipAddress, port);
            out = new PrintStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            throw new RuntimeException("Error connecting to server");
        }
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public void disconnect() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error disconnecting.");
        }
    }

    public JSObject sendRequest(String request) {
        return null;
    }
}
