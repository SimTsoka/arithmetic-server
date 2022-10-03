package Players;

import java.util.HashMap;

public class PlayerDatabase {
    private static HashMap<String, Player> players = new HashMap<>();

    public static void reset() {
        players = new HashMap<>();
    }

    public static HashMap<String, Player> getPlayers() {
        return players;
    }

    public static void addNewPlayer(Player player) {
        players.put(player.getName(), player);
    }

    public static void removePlayer(Player player) {
        players.remove(player.getName());
    }

    public static Player searchPlayer(String name) {
        return players.get(name);
    }
}
