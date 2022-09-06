package Players;

import org.json.JSONArray;

import java.util.ArrayList;

public class NewPlayer {
    private Player player;

    public boolean isAccepted(String command, JSONArray arg) {
        if (command.equalsIgnoreCase("start") && arg.length() == 1) {
            return isNameAlphaNum(arg.getString(0));
        }

        return false;
    }

    /**
     * Method checks if user entered a valid name
     * @param name - name user entered
     * @return true or false
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
}
