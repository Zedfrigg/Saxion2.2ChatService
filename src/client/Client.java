package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	private static final String CONNECT_ADDRESS = "localhost";
	private static final int CONNECT_PORT = 3000;
    
    public static void main(String[] args) {
		
        new Client().run();
    }

    private void run() {

        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
	
		boolean running = true;
		while (running) {
			try {
				socket = new Socket(CONNECT_ADDRESS, CONNECT_PORT);
				running = false;
			} catch (IOException e) {
				System.out.print("[CONN]\tCouldn't connect to server, try again? [y/n] ");
				if (scanner.nextLine().equalsIgnoreCase("n")) {
					System.exit(0);
				}
			}
		}
		
		ReceiveThread receiveThread = new ReceiveThread(socket);
		receiveThread.start();
	
		OutputStream outputStream = null;
		
		try {
			outputStream = socket.getOutputStream();
		} catch (IOException e) {
			System.out.println("[CONN]\tCouldn't get output stream");
			System.exit(1);
		}
		
		PrintWriter writer = new PrintWriter(outputStream);
	
		while (true) {
			System.out.print("       you | ");
			writer.println(scanner.nextLine());
			writer.flush();
		
			// TODO: check if message was delivered / if server is still reachable
		}
    }
}