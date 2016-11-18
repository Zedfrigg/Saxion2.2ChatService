package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private final static int LISTEN_PORT = 3000;
	
    public static void main(String[] args) {
        
        new Server().run();
    }

    private void run() {

        ServerSocket serverSocket = null;
	
		System.out.println("Listening on port " + LISTEN_PORT);
		try {
			serverSocket = new ServerSocket(LISTEN_PORT);
		} catch (IOException e) {
			System.out.println("Couldn't start listening");
			System.exit(1);
		}
		
		while(true) {
			try {
				//continually check for client connections
				Socket socket = serverSocket.accept();
				
				//set up input/output stream
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				
				//start the client thread
				ClientThread clientThread = new ClientThread(socket);
				clientThread.start();
				
				System.out.println("New client connected with address " + socket.getRemoteSocketAddress());
			} catch (IOException e) {
				System.out.println("There was a problem connecting a client");
			}
			
		}
    }
}