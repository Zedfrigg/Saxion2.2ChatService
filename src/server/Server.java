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

        //initialize the server socket
        ServerSocket serverSocket;

        //try to connect to the server socket at socket 3000
        while (true) {
            try {
                serverSocket = new ServerSocket(3000);

                //continually check for client connections
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();

                System.out.println("client connected");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}