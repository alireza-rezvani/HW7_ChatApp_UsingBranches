package ir.maktab32.java.projects.homework7.chatapp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private int serverPort = 8080;

    public static ArrayList<Socket> clients = new ArrayList<>();

    public void execute(){
        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("\u2705 Server Created Successfully!");
            System.out.println("\u29bf Server is Waiting for A Client to Connect!");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);
                for (int i=0; i<clients.size()-1;i++)
                    clients.get(i).close();
                System.out.println("\n\u2705 A New Client is Connected to Server!\n");

                Thread thread = new Thread(new ClientManager(clientSocket));
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().execute();
    }
}
