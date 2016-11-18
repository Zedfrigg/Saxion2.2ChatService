package server;

import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){

    }
}
