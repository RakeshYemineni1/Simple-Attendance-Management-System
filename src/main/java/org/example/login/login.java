package org.example.login;

import org.example.Dashboard.Dashboard;
import org.example.models.*;
import java.util.Scanner;

public class login {
    public static void Login() {
        Scanner sc = new Scanner(System.in);
        User user = null;
        while(user == null) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("                    LOGIN TO AMS PROFILE                       ");
            System.out.println("---------------------------------------------------------------");
            System.out.println();
            System.out.print("      Enter your username: ");
            String username = sc.nextLine();
            System.out.print("      Enter your password: ");
            String password = sc.nextLine();
            System.out.print("      Enter your role(Admin/Staff/Student): "); //Case-sensitive
            String role = sc.nextLine();
            System.out.println();

            user = User.Authenticate(username, password, role);

            if (user != null) {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("    Welcome " + user.getUsername() + " to Attendence Management System");
                System.out.println("----------------------------Login Successful---------------------------");
                Utils.clearScreen();
                Dashboard.getDashboard(user);
            } else {
                System.out.println("Login Failed Invalid Credentials");
            }
        }
    }
}
