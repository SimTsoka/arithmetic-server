package Server.Commands.Validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandValidator {
    private final String[] command;
    private final List<String> noArgsCommands = List.of("shutdown", "players");

    public CommandValidator(String userInput) {
        command = userInput.isEmpty() ? new String[]{} : userInput.split(" ");
    }

    public String validate() {
        if (command.length == 0) {
            return emptyCommand();
        } else if (command.length == 1) {
            return singleCommand();
        }

        return "testing";
    }

    private String emptyCommand() {
        System.out.println("Error! Please enter a command.");
        return "";
    }

    private String singleCommand() {
        return noArgsCommands.contains(command[0].toLowerCase()) ? command[0].toLowerCase() : "";
    }
}
