package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    private final int DEFAULT_PORT = 5000;
    private ServerSocket serverSocket;
    private Socket client;
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Server running");

        while(true) {
            try {
                client = serverSocket.accept();
                System.out.println("Client connected: " + client.isConnected());

                Thread thread = new Thread(new ClientHandler(client));
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
