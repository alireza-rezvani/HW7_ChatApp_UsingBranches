package ir.maktab32.java.projects.homework7.chatapp.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientManager implements Runnable {
    private Scanner scanner = new Scanner(System.in);
    private Socket clientHolder;
    private DataInputStream reader;
    private PrintWriter writer;

    public ClientManager(Socket client){
        this.clientHolder = client;
    }
    @Override
    public void run() {
        try {
            reader = new DataInputStream(clientHolder.getInputStream());
            writer = new PrintWriter(clientHolder.getOutputStream(), true);

            String serverMsg;
            String clientMsg;
            String clientName;
            serverMsg = "Hello Client! What is Your Name?";
            System.out.println("Server: " + serverMsg);
            writer.println(serverMsg);
            clientName =  reader.readLine();
            System.out.println("Client: " + clientName);
            serverMsg = "Welcome " + clientName + "!";
            System.out.println("Server: " + serverMsg);
            writer.println(serverMsg);
            while (true){
                clientMsg = reader.readLine();
                System.out.println(clientName + ": " + clientMsg);

                if (clientMsg.equals("exit"))
                    break;

                System.out.print("Server: ");
                serverMsg = scanner.nextLine();
                writer.println(serverMsg);
            }
        } catch (IOException e) {
            System.out.println("A New Client is Trying to Chat to Server!");
        }
    }

}
