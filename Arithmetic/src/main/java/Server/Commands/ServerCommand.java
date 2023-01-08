package Server.Commands;

import Server.Server;

public abstract class ServerCommand {
    public abstract boolean execute();

    public static Server createCommand(String input) {
        return null;
    }
}
