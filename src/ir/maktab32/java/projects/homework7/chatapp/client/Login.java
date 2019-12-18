package ir.maktab32.java.projects.homework7.chatapp.client;

import ir.maktab32.java.projects.homework7.chatapp.Data.DataSource;
import ir.maktab32.java.projects.homework7.chatapp.model.User;

import java.util.Scanner;

public class Login {
    private Scanner scanner = new Scanner(System.in);
    private String username;
    private String password;
    private String serverIp;
    private String serverPort;

    public void execute(){
        boolean loginAsNewUser = true;

        while (loginAsNewUser) {
            boolean isUserValid = false;
            boolean isServerValid = false;
            System.out.println("Login");
            while (!isUserValid) {
                System.out.print("Username: ");
                username = scanner.next();
                System.out.print("Password: ");
                password = scanner.next();

                for (User i : DataSource.users) {
                    if (i.getUsername().equals(username) && i.getPassword().equals(password))
                        isUserValid = true;
                }
                if (!isUserValid)
                    System.out.println("Invalid Username or Password!");
            }

            while (!isServerValid) {
                System.out.print("Server IP: ");
                serverIp = scanner.next();
                System.out.print("Server Port: ");
                serverPort = scanner.next();

                if ((serverIp.equals("localhost") || serverIp.equals("127.0.0.1")) && serverPort.equals("8080"))
                    isServerValid = true;
                else
                    System.out.println("Invalid IP or Port!");
            }

            System.out.println("Login Successful!");
            new Client().execute();

            System.out.println("Do You Want to Login as Another Client? y/other key");
            String answer = scanner.next();
            if (!answer.equals("y"))
                loginAsNewUser = false;
        }
    }
}
