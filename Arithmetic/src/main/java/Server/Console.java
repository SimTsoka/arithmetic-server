package Server;

import Server.ServerCommands.ServerCommand;
import Server.ServerCommands.Validation.CommandValidator;

import java.io.InputStream;
import java.util.Scanner;

public class Console implements Runnable{
    private boolean serverOn;
    private Scanner scanner;
    private boolean isTest = false;

    public void initialise() {
        initialise(System.in);
    }

    public void initialise(InputStream inputStream) {
        Printer.printMsg("Initialising Server Console.");
        serverOn = true;
        scanner = new Scanner(inputStream);
    }

    public void testOn() {
        isTest = true;
    }

    public void testOff() {
        isTest = false;
    }

    @Override
    public void run() {
        while (serverOn) {
            String userInput = checkInput();
            ServerCommand serverCommand = ServerCommand.createCommand(userInput);
            serverOn = serverCommand.execute();
        }

        //TODO:Client will handle shutdown message
        if (!isTest) {
            System.exit(0);
        }
    }

    private String checkInput() {
        String userInput;

        while ("".equals(userInput = new CommandValidator(scanner.nextLine()).validate())) {
        }

        return userInput;
    }
}
