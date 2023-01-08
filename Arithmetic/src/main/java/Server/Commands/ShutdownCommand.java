package Server.Commands;

public class ShutdownCommand extends ServerCommand {

    public ShutdownCommand() {
        super.name = "shutdown";
    }

    @Override
    public boolean execute() {
        System.out.println("Shutting Down..");
        return false;
    }
}
