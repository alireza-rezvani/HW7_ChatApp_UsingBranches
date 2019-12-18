package ir.maktab32.java.projects.homework7.chatapp.client;

import ir.maktab32.java.projects.homework7.chatapp.Data.DataSource;
import ir.maktab32.java.projects.homework7.chatapp.model.User;

import java.util.Scanner;

public class Login {
    private static Scanner scanner = new Scanner(System.in);
    private static String username;
    private static String password;
    private static String serverIp;
    private static String serverPort;

    public static void execute(){
        boolean loginAsNewUser = true;

        while (loginAsNewUser) {
            boolean isUserValid = false;
            boolean isServerValid = false;
            System.out.println("\u2705 Login");
            while (!isUserValid) {
                System.out.print("\t\u29bf Username: ");
                username = scanner.next();
                System.out.print("\t\u29bf Password: ");
                password = scanner.next();

                for (User i : DataSource.users) {
                    if (i.getUsername().equals(username) && i.getPassword().equals(password))
                        isUserValid = true;
                }
                if (!isUserValid)
                    System.out.println("\t\t\u26a0 Invalid Username or Password!");
            }

            while (!isServerValid) {
                System.out.print("\t\u29bf Server IP: ");
                serverIp = scanner.next();
                System.out.print("\t\u29bf Server Port: ");
                serverPort = scanner.next();

                if ((serverIp.equals("localhost") || serverIp.equals("127.0.0.1")) && serverPort.equals("8080"))
                    isServerValid = true;
                else
                    System.out.println("\t\t\u26a0 Invalid IP or Port!");
            }

            System.out.println("\t\t\u2705 Login Successful!");
            new Client().execute();

            System.out.println("\t\u29bf Do You Want to Login as Another Client?\t(y: Yes | other keys: No)");
            String answer = scanner.next();
            if (!answer.equals("y"))
                loginAsNewUser = false;
        }
    }
}
