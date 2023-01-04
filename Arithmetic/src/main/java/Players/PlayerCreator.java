package Players;

import org.json.JSONArray;

public class PlayerCreator {
    private Player player;

    /**
     * This method checks if user enter the correct start command e.g "start simon".
     * If successful a new Player object is created and added to the player database.
     * @param command The command the user entered.
     * @param arg The supporting arguments for the command entered.
     * @return Boolean that determines whether user entered command correctly.
     */
    public boolean isAccepted(String command, JSONArray arg) {
        if (command.equalsIgnoreCase("start") && arg.length() == 1 &&
                isNameAlphaNum(arg.getString(0)) &&
                !isPlayerInDatabase(arg.getString(0))) {
            createPlayer(arg.getString(0));
            PlayerDatabase.addNewPlayer(getPlayer());
            return true;
        }

        return false;
    }

    /**
     * Method checks if user entered a valid name
     * @param name - Name user entered
     * @return Boolean that determines whether name was entered in correct format
     */
    public boolean isNameAlphaNum(String name) {
        int countAlpha = 0;
        for (int i = 0; i < name.length(); i++) {
            if (!(Character.isLetterOrDigit(name.charAt(i)))) {
                return false;
            }

            if (Character.isLetter(name.charAt(i))) {
                countAlpha++;
            }
        }

        return countAlpha > 0;
    }

    /**
     * Method creates a new player object.
     * @param name Name of player
     */
    public void createPlayer(String name) {
        player = new Player(name);
    }

    /**
     * Getter for player object
     * @return player object
     */
    public Player getPlayer() {
        return player;
    }

    public boolean isPlayerInDatabase(String name) {
        return PlayerDatabase.searchPlayer(name) != null;
    }
}
