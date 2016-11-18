package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        new Client().run();
    }

    private void run() {
        Socket socket;
         try {
             socket = new Socket("localhost",3000);

             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();

         } catch (UnknownHostException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
