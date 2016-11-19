package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class SendThread extends Thread {
	
	private final Socket sender;
	private final List<Socket> clientSocketList;
	private final String message;
	
	public SendThread(Socket sender, List clientSocketList, String message) {
		
		this.sender = sender;
		this.clientSocketList = clientSocketList;
		this.message = message;
	}
	
	@Override
	public void run() {
		
		for (Socket currSocket : clientSocketList) {
			if (currSocket != sender) {
				
				OutputStream outputStream;
				
				try {
					outputStream = currSocket.getOutputStream();
				} catch (IOException e) {
					System.out.println("[CONN]\tCouldn't get output stream");
					continue;
				}
				
				PrintWriter writer = new PrintWriter(outputStream);
				
				writer.println(message);
				writer.flush();
			}
		}
	}
}