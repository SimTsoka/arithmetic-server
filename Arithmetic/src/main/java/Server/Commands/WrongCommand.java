package Server.Commands;

public class WrongCommand extends ServerCommand{

    @Override
    public boolean execute() {
        System.out.println("Error! Please enter a valid server command.");

        return true;
    }
}
