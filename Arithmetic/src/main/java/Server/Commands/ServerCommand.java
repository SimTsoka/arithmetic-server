package Server.Commands;

import Server.Server;

public abstract class ServerCommand {
    protected String name;

    public String getName() {
        return name;
    }

    public abstract boolean execute();

    public static ServerCommand createCommand(String input) {
        return switch (input.toLowerCase()) {
            case "shutdown" -> new ShutdownCommand();
            case "players" -> new PlayersCommand();
            default -> null;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof ServerCommand) {
            return this.name.equals(((ServerCommand) o).getName());
        } else {
            return false;
        }
    }
}
