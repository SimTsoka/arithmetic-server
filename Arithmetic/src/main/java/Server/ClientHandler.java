  package Server;

import Players.PlayerCreator;
import Players.Player;
import Players.PlayerDatabase;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket socket;
    private PrintStream out;
    private BufferedReader in;
    private String messageFromClient;
    private static boolean isCreated = false;
    private static Player player;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        initialiseStreams();
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
                    printToConsole(response);
                }

                out.println(response);
                out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            shutdown();
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
        PlayerCreator playerCreator =  new PlayerCreator();

        if (playerCreator.isAccepted(msg.getString("command"), msg.getJSONArray("arg"))) {
            setPlayer(playerCreator.getPlayer());
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

    public static void printToConsole(JSONObject response) {
        System.out.printf("%1s: %2s%n", response.get("name"), response.get("message"));
    }

    public void initialiseStreams() throws IOException {
        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void shutdown() {
        try {
            PlayerDatabase.removePlayer(player);
            out.close();
            in.close();
            socket.close();
            reset();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
