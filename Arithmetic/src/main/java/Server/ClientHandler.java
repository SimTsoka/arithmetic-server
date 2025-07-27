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
    private boolean isCreated = false;
    private Player player;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        initialiseStreams();
    }

    public ClientHandler() {
        this.socket = null;
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
                } else {

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

    public JSONObject intro() {
        JSONObject response = new JSONObject();
        String message = "Welcome to Arithmetics!!!\n" +
                "To get started, please enter \"start\" followed by your username.\n";
        response.put("message", message);
        return response;
    }

    public JSONObject checkStartRequirements(JSONObject msg) {
        PlayerCreator playerCreator =  new PlayerCreator();

        if (playerCreator.isAccepted(msg.getString("command"), msg.getJSONArray("arg"))) {
            setPlayer(playerCreator.getPlayer());
            return new JSONParser().successfulMessage("You have successfully launched into the program.\n",
                    player.getName());
        } else {
            return new JSONParser().errorMessage("Please enter \"start\" followed by your username.\n");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player newPlayer) {
        player = newPlayer;
        isCreated = true;
    }

    public boolean isPlayerCreated() {
        return isCreated;
    }

    public void reset() {
        player = null;
        isCreated = false;
    }

    public void printToConsole(JSONObject response) {
        if (response.get("message").equals("Please enter \"start\" followed by your username.\n")) {
            System.out.println("User didn't launch correctly\n");
        } else {
            System.out.printf("%1s: %2s%n", response.get("name"), response.get("message"));
        }
    }

    public void initialiseStreams() throws IOException {
        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void shutdown() {
        try {
            PlayerDatabase.removePlayer(player);
            Printer.printMsg(player.getName() + " removed from the database.");
        } catch (NullPointerException e) {
            System.out.println("Player doesn't exist in database");
        }

        try {

            out.close();
            in.close();
            socket.close();
            reset();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
