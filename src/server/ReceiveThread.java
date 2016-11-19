package server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ReceiveThread extends Thread {
    
    private final Socket socket;
	private final List clientSocketList;
	
	public ReceiveThread(Socket socket, List clientSocketList) {
		
        this.socket = socket;
		this.clientSocketList = clientSocketList;
	}

    @Override
	public void run() {
	
		InputStream inputStream;
	
		// Set up input stream
		try {
			inputStream = socket.getInputStream();
		} catch (IOException e) {
			System.out.println("[CONN]\tCouldn't get streams");
			return;
		}
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	
		while (true) {
			try {
				
				String line = reader.readLine();
				System.out.println("[MESG]\t\"" + line + "\"");
				
				// Forward the message to all other clients
				SendThread sendThread = new SendThread(socket, clientSocketList, line);
				sendThread.start();
				
			} catch (IOException e) {
				// Couldn't read next message, assuming client disconnected
				System.out.println("[CONN]\tClient disconnected");
				clientSocketList.remove(socket);
				return;
			}
		}
    }
}