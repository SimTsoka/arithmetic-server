package Server;

import java.util.Scanner;

public class Console implements Runnable{
    private boolean serverOn;
    private Scanner scanner;

    public void initialise() {
        serverOn = true;
        scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (serverOn) {

        }
    }
}
