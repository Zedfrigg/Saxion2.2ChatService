package server;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    
    private final Socket socket;
	private int threadNr = 0;
	
	public ClientThread(Socket socket) {
		
        this.socket = socket;
    }

    public void run() {
		
		// The setThreadNr method should've been called before this
		assert threadNr != 0;
	
		InputStream inputStream;
		OutputStream outputStream;

		boolean running = true;
		while (running) {
			if (socket.isConnected()){
				running = false;
			}
		}
	
		//set up input/output stream
		try {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
		} catch (IOException e) {
			System.out.println("Couldn't get streams");
			return;
		}
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	
		while (true) {
			try {
				
				String line = reader.readLine();
				System.out.println(line);
				
			} catch (IOException e) {
				// Couldn't read next message, assuming client disconnected
				System.out.println("Client disconnected");
				return;
			}
		}
    }
    
    public int getThreadNr() {
		
		return threadNr;
	}
	
	public void setThreadNr(int threadNr) {
		
		this.threadNr = threadNr;
	}
}