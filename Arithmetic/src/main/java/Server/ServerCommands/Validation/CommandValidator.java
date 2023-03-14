package Server.ServerCommands.Validation;

import java.util.List;

public class CommandValidator {
    private final String[] command;
    private final List<String> noArgsCommands = List.of("shutdown", "players", "help");

    public CommandValidator(String userInput) {
        command = userInput.isEmpty() ? new String[]{} : userInput.split(" ");
    }

    public String validate() {
        if (command.length == 0) {
            return emptyCommand();
        } else if (command.length == 1) {
            return singleCommand();
        } else {
            return invalidCommand();
        }
    }

    private String emptyCommand() {
        System.out.println("Error! Please enter a valid server command.");
        return "";
    }

    private String singleCommand() {
        if (noArgsCommands.contains(command[0].toLowerCase())) {
            return command[0].toLowerCase();
        } else {
            return invalidCommand();
        }
    }

    private String invalidCommand() {
        System.out.println("Invalid Command! Enter \"help\" for a list of valid commands.");
        return "";
    }
}
