package ir.maktab32.java.projects.homework7.chatapp.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    Scanner scanner = new Scanner(System.in);
    Socket clientSocket;
    String serverAddress = "localhost";
    int serverPort = 8080;

    DataInputStream reader;
    PrintWriter writer;

    public void execute(){
        try {
            clientSocket = new Socket(serverAddress, serverPort);
            System.out.println("\u2705 Client Created And Connected to the Server Successfully!\n");

            reader = new DataInputStream(clientSocket.getInputStream());
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

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
            System.out.println("\u26a0 Another Client is Chatting to Server!");
        }
    }

}
