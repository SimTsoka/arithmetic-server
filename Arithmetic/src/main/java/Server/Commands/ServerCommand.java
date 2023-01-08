package Server.Commands;

import Server.Server;

public abstract class ServerCommand {
    protected String name;

    public String getName() {
        return name;
    }

    public abstract boolean execute();

    public static Server createCommand(String input) {
        return null;
    }
}
