package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }

    private void run() {

        //initialize the server socket
        ServerSocket serverSocket = null;


        try {
            serverSocket = new ServerSocket(3000);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //try to connect to the server socket at socket 3000
        while (true) {
            try {


                //continually check for client connections
                Socket socket = serverSocket.accept();

                //set up input/output stream
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                //start the client thread
                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();

                System.out.println("client connected");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}