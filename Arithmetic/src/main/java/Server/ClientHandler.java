package Server;

import Players.NewPlayer;
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
    private String messageFromClient;
    private boolean isCreated = false;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        out.println(intro());

        try {
            while ((messageFromClient = in.readLine()) != null) {
                JSONObject msg = new JSONObject(messageFromClient);

                if (!isCreated) {

                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    public static JSONObject intro() {
        JSONObject response = new JSONObject();
        String message = "Welcome to Arithmetics!!!\n" +
                "To get started, please enter \"start\" followed by your username.\n";
        response.put("message", message);
        return response;
    }

    public static JSONObject checkStartRequirements(JSONObject msg) {
        NewPlayer newPlayer =  new NewPlayer();
        JSONObject response = new JSONObject();

        if (newPlayer.isAccepted(msg.getString("command"), msg.getJSONArray("arg"))) {
            response.put("result", "OK");
            response.put("message", "You have successfully launched into the program.\n");
            response.put("name", msg.getJSONArray("arg").get(0));
        } else {
            response.put("result", "ERROR");
            response.put("message", "Please enter \"start\" followed by your username.\n");
        }
        return response;
    }
}
