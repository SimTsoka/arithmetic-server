package Players;

import java.util.ArrayList;

public class PlayerList {
    private static ArrayList<Player> players = new ArrayList<>();

    public static void reset() {
        players = new ArrayList<>();
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void addNewPlayer(Player player) {
        players.add(player);
    }

    public static void removePlayer(Player player) {
        players.remove(player);
    }
}
