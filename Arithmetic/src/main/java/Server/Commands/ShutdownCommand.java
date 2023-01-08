package Server.Commands;

public class ShutdownCommand extends ServerCommand {
    private final String name;

    public ShutdownCommand() {
        name = "shutdown";
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean execute() {
        System.out.println("Shutting Down..");
        return false;
    }
}
