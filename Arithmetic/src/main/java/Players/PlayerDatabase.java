package Players;

import java.util.HashMap;

public class PlayerDatabase {
    private static HashMap<String, Player> players = new HashMap<>();

    /**
     * This method resets the HashMap of players.
     */
    public static void reset() {
        players = new HashMap<>();
    }

    /**
     * Getter for the HashMap of players.
     * @return HashMap of Players
     */
    public static HashMap<String, Player> getPlayers() {
        return players;
    }

    /**
     * Adds new player to HashMap of players.
     * @param player Player object to be added.
     */
    public static void addNewPlayer(Player player) {
        players.put(player.getName(), player);
    }

    /**
     * Removes a player from the HashMap of players.
     * @param player Player object to be removed.
     */
    public static void removePlayer(Player player) {
        players.remove(player.getName());
    }

    /**
     * Searches for a player within the HashMap of players.
     * @param name Name of player to be searched.
     * @return Player object.
     */
    public static Player searchPlayer(String name) {
        return players.get(name);
    }
}
