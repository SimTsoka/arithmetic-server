package Server;

import Server.Exceptions.ConsoleWillNotStartException;
import Server.ServerCommands.ServerCommand;
import Server.ServerCommands.Validation.CommandValidator;

import java.io.InputStream;
import java.util.NoSuchElementException;
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
        try {
            while (serverOn) {
                String userInput = checkInput();
                ServerCommand serverCommand = ServerCommand.createCommand(userInput);
                serverOn = serverCommand.execute();
            }

            //TODO:Client will handle shutdown message
            if (!isTest) {
                System.exit(0);
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            System.err.println("No line found or scanner is closed. Server commands will not be able to be run");
        }
    }

    private String checkInput() throws NoSuchElementException, IllegalStateException {
        String userInput;

        while ("".equals(userInput = new CommandValidator(scanner.nextLine()).validate())) {
        }

        return userInput;
    }
}
