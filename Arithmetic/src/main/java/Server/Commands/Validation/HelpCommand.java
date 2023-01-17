package Server.Commands.Validation;

import Server.Commands.ServerCommand;

public class HelpCommand extends ServerCommand {

    public HelpCommand() {
        super.name = "help";
    }

    @Override
    public boolean execute() {
        System.out.print("""
                HERE IS A LIST OF COMMANDS:
                Help - For more information on commands.
                Players - Returns a list of players that have launched into the program.
                Shutdown - Shuts the whole server down.
                """);

        return true;
    }
}
