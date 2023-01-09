package Server.Commands;

public class PlayersCommand extends ServerCommand{

    public PlayersCommand() {
        super.name = "players";
    }

    @Override
    public boolean execute() {
        return false;
    }
}
