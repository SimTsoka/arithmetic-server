package Server;

import Server.Commands.ServerCommand;
import Server.Commands.Validation.CommandValidator;

import java.io.InputStream;
import java.util.Scanner;

public class Console implements Runnable{
    private boolean serverOn;
    private Scanner scanner;

    public void initialise() {
        initialise(System.in);
    }

    public void initialise(InputStream inputStream) {
        Printer.printMsg("Initialising Server Console.");
        serverOn = true;
        scanner = new Scanner(inputStream);
    }

    @Override
    public void run() {
        while (serverOn) {
            String userInput = checkInput();
            ServerCommand serverCommand = ServerCommand.createCommand(userInput);
            serverOn = serverCommand.execute();
        }

        //TODO:Exit works but need to find a way to send a message to the client, and close all streams
        System.exit(0);
    }

    private String checkInput() {
        String userInput;

        while ("".equals(userInput = new CommandValidator(scanner.nextLine()).validate())) {
        }

        return userInput;
    }
}
