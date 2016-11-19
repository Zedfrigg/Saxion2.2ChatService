package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread {
	
	private final Socket socket;
	
	public ReceiveThread(Socket socket) {
		
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
		InputStream inputStream = null;
		
		try {
			inputStream = socket.getInputStream();
		} catch (IOException e) {
			System.out.println("[CONN]\tCouldn't get input stream");
			return;
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		while (true) {
			try {
				
				String line = reader.readLine();
				System.out.println("\r     other | " + line);
				System.out.print("       you | ");
				
			} catch (IOException e) {
				System.out.println("[CONN]\tCouldn't reach server");
				return;
			}
		}
	}
}