package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Server {
	
	private static final int LISTENING_PORT = 3000;
	
    public static void main(String[] args) {
		
        new Server().run();
    }

    private void run() {
		
		ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
        } catch (IOException e) {
			System.out.println("Couldn't create server socket");
			System.exit(1);
		}
		
        while (true) {
            try {
				
                // continually check for client connections
                Socket socket = serverSocket.accept();

                // start the client thread and add it to the list
                ClientThread clientThread = new ClientThread(socket);
				// TODO: add a way to identify client threads / connections
				clientThread.start();

                System.out.println("Client connected from: " + socket.getRemoteSocketAddress());

            } catch (IOException e) {
				System.out.println("Problem with socket");
			}
        }
    }
}