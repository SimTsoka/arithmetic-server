  package Server;

import Players.NewPlayer;
import Players.Player;
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
    private static boolean isCreated = false;
    private static Player player;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        JSONObject response = new JSONObject();

        out.println(intro());

        try {
            while ((messageFromClient = in.readLine()) != null) {
                JSONObject msg = new JSONObject(messageFromClient);

                if (!isCreated) {
                    response = checkStartRequirements(msg);
                }

                out.println(response);
                out.flush();
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

        if (newPlayer.isAccepted(msg.getString("command"), msg.getJSONArray("arg"))) {
            setPlayer(newPlayer.getPlayer());
            return new JSONParser().successfulMessage("You have successfully launched into the program.\n",
                    player.getName());
        } else {
            return new JSONParser().errorMessage("Please enter \"start\" followed by your username.\n");
        }
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player newPlayer) {
        player = newPlayer;
        isCreated = true;
    }

    public static boolean isPlayerCreated() {
        return isCreated;
    }

    public static void reset() {
        player = null;
        isCreated = false;
    }
}
