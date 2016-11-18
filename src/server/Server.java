package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }

    private void run() {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(3000);

            while(true) {
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
