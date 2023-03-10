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
    }

    private String checkInput() {
        String userInput;

        while ("".equals(userInput = new CommandValidator(scanner.nextLine()).validate())) {
        }

        return userInput;
    }
}
