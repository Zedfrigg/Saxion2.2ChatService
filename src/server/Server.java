package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	private static final int LISTENING_PORT = 3000;
	
    public static void main(String[] args) {
		
        new Server().run();
    }

    private void run() {
		
		ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
			System.out.println("[CONN]\tListening on port " + LISTENING_PORT);
		} catch (IOException e) {
			System.out.println("[CONN]\tCouldn't create server socket");
			System.exit(1);
		}
		
		final List<Socket> clientSocketList = new ArrayList<>();
		
        while (true) {
            try {
				
                // Continually check for client connections
                Socket socket = serverSocket.accept();

                // Start a client thread and add the socket to the list
                ReceiveThread receiveThread = new ReceiveThread(socket, clientSocketList);
				clientSocketList.add(socket);
				// TODO: add a way to identify client threads / connections
				receiveThread.start();

                System.out.println("[CONN]\tClient connected from: " + socket.getRemoteSocketAddress());

            } catch (IOException e) {
				System.out.println("[CONN]\tProblem with socket");
				System.exit(1);
			}
        }
    }
}