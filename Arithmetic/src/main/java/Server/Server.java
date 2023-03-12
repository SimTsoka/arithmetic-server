package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    private ServerSocket serverSocket;

    @Override
    public void run() {
        createServerSocket();
        startConsoleThread();
        checkIncomingConnections();
    }

    public static void main(String[] args) {
        Server server = new Server();
        Printer.printMsg("Starting server..");
        server.run();
    }

    private void createServerSocket() {
        try {
            Printer.printMsg("Creating server socket..");
            int DEFAULT_PORT = 5000;
            serverSocket = new ServerSocket(DEFAULT_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Printer.printMsg("Server socket created successfully\n");
    }

    private void startConsoleThread() {
        Console serverConsole = new Console();
        serverConsole.initialise();

        Printer.printMsg("Starting Server Console thread.");
        Thread thread = new Thread(serverConsole);
        thread.start();
        Printer.printMsg("Thread started successfully\n");
    }

    private void checkIncomingConnections() {
        Printer.printMsg("Checking incoming connections..");

        while(true) {
            try {
                Socket client = serverSocket.accept();
                Printer.printMsg("Client connected: " + client.isConnected());

                Thread thread = new Thread(new ClientHandler(client));
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
