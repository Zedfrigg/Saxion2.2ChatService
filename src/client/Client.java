package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        new Client().run();
    }

    private void run() {

        Scanner scanner = new Scanner(System.in);

        Socket socket;

         try {
             socket = new Socket("localhost",3000);

             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();

             //send a line of text
             PrintWriter writer = new PrintWriter(outputStream);

             while (true) {
                 System.out.print("> ");
                 writer.println(scanner.next());
                 writer.flush();
             }

         } catch (UnknownHostException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
