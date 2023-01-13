package Server.Commands;

public class WrongCommand extends ServerCommand{

    public WrongCommand() {
        super.name = "wrong";
    }
    @Override
    public boolean execute() {
        System.out.println("Error! Please enter a valid server command.");

        return true;
    }
}
