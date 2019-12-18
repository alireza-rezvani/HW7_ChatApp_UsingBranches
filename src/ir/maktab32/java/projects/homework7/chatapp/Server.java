package ir.maktab32.java.projects.homework7.chatapp;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private Scanner scanner = new Scanner(System.in);
    private ServerSocket server;
    private int port = 8080;
    private DataInputStream reader;
    private PrintWriter writer;

    public void execute(){
        try {
            server = new ServerSocket(port);
            System.out.println("\u2705 Server Created Successfully!");

            while (true) {
                System.out.println("\n\u29bf Server is Waiting for Clients...");
                Socket client = server.accept();
                System.out.println("\u2705 A Client Connected to Server Successfully!\n");

                reader = new DataInputStream(client.getInputStream());
                writer = new PrintWriter(client.getOutputStream(), true);

                String serverMsg;
                String clientMsg;
                String clientName;
                serverMsg = "Hello Client! What is Your Name?";
                System.out.println("Server: " + serverMsg);
                writer.println(serverMsg);
                clientName = reader.readLine();
                System.out.println("Client: " + clientName);
                serverMsg = "Welcome " + clientName + "!";
                System.out.println("Server: " + serverMsg);
                writer.println(serverMsg);

                while (true) {
                    clientMsg = reader.readLine();
                    System.out.println(clientName + ": " + clientMsg);

                    if (clientMsg.equals("exit"))
                        break;

                    System.out.print("Server: ");
                    serverMsg = scanner.nextLine();
                    writer.println(serverMsg);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().execute();
    }

}