package Server;

import Server.Commands.ServerCommand;

import java.io.InputStream;
import java.util.Scanner;

public class Console implements Runnable{
    private boolean serverOn;
    private Scanner scanner;

    public void initialise() {
        initialise(System.in);
    }

    public void initialise(InputStream inputStream) {
        serverOn = true;
        scanner = new Scanner(inputStream);
    }

    @Override
    public void run() {
        while (serverOn) {
            String userInput = scanner.nextLine();
            ServerCommand serverCommand = ServerCommand.createCommand(userInput);
            serverOn = serverCommand.execute();
        }
    }
}
