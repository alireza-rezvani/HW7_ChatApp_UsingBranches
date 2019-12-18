package ir.maktab32.java.projects.homework7.chatapp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    Scanner scanner = new Scanner(System.in);
    private String serverIp = "localhost";
    private int port = 8080;
    private Socket client;
    private DataInputStream reader;
    private PrintWriter writer;

    public void execute(){
        try {
            client = new Socket(serverIp, port);
            System.out.println("\u2705 Client Connected to Server!");
            System.out.println("\u2705 You Can Start Chatting Now!\t(Type 'exit' to Quit Chat)\n");

            reader = new DataInputStream(client.getInputStream());
            writer = new PrintWriter(client.getOutputStream(), true);
            
            String clientMessage;
            String serverMessage;
            String clientName;
            serverMessage = reader.readLine();
            System.out.println("Server: " + serverMessage);
            System.out.print("Client: ");
            clientName = scanner.nextLine();
            writer.println(clientName);
            serverMessage = reader.readLine();
            System.out.println("Server: " + serverMessage);

            while (true){
                System.out.print(clientName + ": ");
                clientMessage = scanner.nextLine();

                writer.println(clientMessage);

                if (clientMessage.equals("exit"))
                    break;

                serverMessage = reader.readLine();
                System.out.println("Server: " + serverMessage);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().execute();
    }

}
