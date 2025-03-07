package org.example;

import org.example.*;
import java.util.Scanner;
import org.example.login.login;
import org.example.models.User;
import org.example.models.Utils;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
        System.out.println("---------------------------------------------------------------");
        System.out.println("            Welcome to Attendance Management System            ");
        System.out.println("---------------------------------------------------------------");
        System.out.println();
        System.out.println("        1. Sign Up to Attendance Management System");
        System.out.println("        2. Login to Attendance Management System");
        System.out.println("        3. Delete your account");
        System.out.println("        4. Exit");
        System.out.println();
        System.out.println("---------------------------------------------------------------");
        System.out.print("  Enter Your Choice: ");
        switch(sc.nextInt()) {
            case 1:
                Utils.clearScreen();
                System.out.println("---------------------------------------------------------------");
                System.out.println("                     CREATE AMS PROFILE                        ");
                System.out.println("---------------------------------------------------------------");
                System.out.print("      ~ Enter Your role(Admin/Staff/Student): ");
                String role = sc.next();
                User newUser = new User();
                newUser.getRole(role);
                break;
            case 2:
                Utils.clearScreen();
                login.Login();
                break;
            case 3:
                Utils.clearScreen();
                System.out.println("---------------------------------------------------------------");
                System.out.println("                     DELETE AMS PROFILE                        ");
                System.out.println("---------------------------------------------------------------");
                System.out.print("     ~ Enter your username");
                String username = sc.next();
                System.out.print("     ~ Enter your password");
                String password = sc.next();
                System.out.print("     ~ Enter your role");
                String role1 = sc.next();
                if (User.deleteUser(username, role1, password)) {
                    System.out.print("Account Deleted Successfully");
                } else {
                    System.out.print("Account Not Deleted. Try Again");
                }
                break;
            case 4:
                System.out.println("Exiting...");
                exit(0);
                break;
            default:
                System.out.println("Invalid Choice. Please try again");
        }
        }

        //Credits : RAKESH YEMINENI
    }
}