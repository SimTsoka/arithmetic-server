package Server.Commands;

import Players.Player;
import Players.PlayerDatabase;

import java.util.HashMap;

public class PlayersCommand extends ServerCommand{

    public PlayersCommand() {
        super.name = "players";
    }

    @Override
    public boolean execute() {

        HashMap<String, Player> players = PlayerDatabase.getPlayers();

        if (players.isEmpty()) {
            System.out.println("No players found.");
        } else {
            System.out.println("Number of players = "+players.keySet().size());
            System.out.println("Players:");

            for (String name: players.keySet().stream().sorted().toList()) {
                System.out.println(name);
            }
        }

        return true;
    }
}
