package Server;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket socket;
    private final PrintStream out;
    private final BufferedReader in;
    private JSONObject messageFromClient;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        out.println(intro());
    }

    public static JSONObject intro() {
        JSONObject response = new JSONObject();
        String message = "Welcome to Arithmetics!!!\n" +
                "To get started, please enter \"start\" followed by your name.\n";
        response.put("message", message);
        return response;
    }
}
