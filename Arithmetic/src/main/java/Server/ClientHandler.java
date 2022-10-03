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
    private boolean isCreated = false;
    private static String name;

    private static Player player;

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
                //now add player to list of players and return successful message to client

                if (!isCreated) {
//                    isCreated = new NewPlayer().isAccepted();
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

        if (newPlayer.isAccepted(msg.getString("command"), msg.getJSONArray("arg"))) {
            setName(msg.getJSONArray("arg").get(0).toString());
            return new JSONParser().successfulMessage("You have successfully launched into the program.\n",
                    getName());
        } else {
            return new JSONParser().errorMessage("Please enter \"start\" followed by your username.\n");
        }
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player newPlayer) {
        player = newPlayer;
    }
    public static String getName() {
        return name;
    }

    public static void setName(String newName) {
        name = newName;
    }

    public static void reset() {
        player = null;
    }
}
