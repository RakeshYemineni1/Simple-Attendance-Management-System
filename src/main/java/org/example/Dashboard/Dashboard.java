package org.example.Dashboard;

import org.example.models.User;
import java.util.Scanner;
import static java.lang.System.exit;

import org.example.models.Utils;
import org.example.models.attendence;

public class Dashboard {
    public static void getDashboard(User user){
        System.out.println("---------DASHBOARD--------");
        System.out.println("Welcome " + user.getName());
        System.out.println("Role: " + user.getRole());
        System.out.println("---------------------------");
        Utils.clearScreen();
        switch (user.getRole()) {
            case "Admin":
                adminDashboard();
                break;
            case "Staff":
                staffDashboard();
                break;
            case "Student":
                studentDashboard();
                break;
            default:
                System.out.println("Invalid Role. Please try again");
                break;
        }

    }


    public static void adminDashboard() {
        while(true){
            System.out.println("---------------------------------------------------------------");
            System.out.println("                        ADMIN DASHBOARD                          ");
            System.out.println("---------------------------------------------------------------");
            System.out.println();
            System.out.println("        1. Add Staff");
            System.out.println("        2. Add Student");
            System.out.println("        3. Add Course");
            System.out.println("        4. Add Attendance");
            System.out.println("        5. Generate Attendance Report");
            System.out.println("        6. Remove Staff");
            System.out.println("        7. Remove Student");
            System.out.println("        8. Get Data ");
            System.out.println("        9. Remove Attendance");
            System.out.println("        10. Logout");
            System.out.println("---------------------------------------------------------------");
            System.out.print("        Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    Utils.clearScreen();
                    addStaff();
                    break;
                case 2:
                    Utils.clearScreen();
                    addStudent();
                    break;
                case 3:
                    Utils.clearScreen();
                    addCourse();
                    break;
                case 4:
                    Utils.clearScreen();
                    addAttendence();
                    break;
                case 5:
                    Utils.clearScreen();
                    generateAttendance();
                    break;
                case 6:
                    Utils.clearScreen();
                    removeStaff();
                    break;
                case 7:
                    Utils.clearScreen();
                    removeStudent();
                    break;
                case 8:
                    Utils.clearScreen();
                    getDataUser();
                    break;
                case 9:
                    Utils.clearScreen();
                    removeAttendence();
                    break;
                case 10:
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice. Please try again");
                    break;
            }
        }
    }

    public static void staffDashboard(){
        while(true) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("                        STAFF DASHBOARD                        ");
            System.out.println("---------------------------------------------------------------");
            System.out.println();
            System.out.println("        1. Add Attendance");
            System.out.println("        2. Generate Attendance Report");
            System.out.println("        3. Remove Attendance");
            System.out.println("        4. Logout");
            System.out.println();
            System.out.println("        Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Utils.clearScreen();
                    addAttendence();
                    break;
                case 2:
                    Utils.clearScreen();
                    generateAttendance();
                    break;
                case 3:
                    Utils.clearScreen();
                    removeAttendence();
                    break;
                case 4:
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice. Please try again");
                    break;
            }
        }
    }

    public static void studentDashboard(){
        while(true) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("                      STUDENT DASHBOARD                        ");
            System.out.println("---------------------------------------------------------------");
            System.out.println();
            System.out.println("    1. Generate Attendance Report");
            System.out.println("    2. Courses Enrolled");
            System.out.println("    3. Logout");
            System.out.println("---------------------------------------------------------------");
            System.out.print("    Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Utils.clearScreen();
                    generateAttendance();
                    break;
                case 2:
                    coursesEnrolled();

                    break;
                case 3:
                    exit(0);
                    break;
            }
        }
    }

    public static void addStaff(){
        System.out.println("---------------------------------------------------------------");
        System.out.println("                          ADD STAFF                            ");
        System.out.println("---------------------------------------------------------------");
        System.out.println();
        System.out.print("        Enter Staff name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.print("        Enter Username: ");
        String username = scanner.next();
        System.out.println("       Enter Password");
        String password = scanner.next();
        System.out.println("---------------------------------------------------------------");
        User newUser = new User(name, "Staff", username, password);

        if(newUser.insertData("Staff")){
            System.out.println("        Staff Added Successfully");
            System.out.println("---------------------------------------------------------------");

        }else {
            System.out.println("Staff Not Added. Try Again");
            System.out.println("---------------------------------------------------------------");

        }
    }
    public static void addStudent(){
        System.out.println("---------------------------------------------------------------");
        System.out.println("                         ADD STUDENT"                           );
        System.out.println("---------------------------------------------------------------");
        System.out.println();
        System.out.print("        Enter Student name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.print("        Enter Username: ");
        String username = scanner.next();
        System.out.print("        Enter Password: ");
        String password = scanner.next();
        System.out.println("---------------------------------------------------------------");
        User newUser = new User(name, "Student", username, password);

        if(newUser.insertData("Student")){
            System.out.println("    Student Added Successfully");
            System.out.println("---------------------------------------------------------------");

        }else {
            System.out.println("    Student Not Added. Try Again");
            System.out.println("---------------------------------------------------------------");

        }
    }
    public static void addCourse(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("We are Working on it.... All features related to Course will be available soon");
        System.out.println("features like Marking Attendance via courses Enrolled by students etc...");
        System.out.println("Please check back later");
        System.out.println("--------------------------------------------------------------------------------");

    }
    public static void addAttendence(){
        System.out.println("---------------------------------------------------------------");
        System.out.println("                         ADD ATTENDANCE                        ");
        System.out.println("---------------------------------------------------------------");
        System.out.println();
        System.out.print("        Enter Student ID: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        System.out.print("        Enter if Present or Absent(P/A):");
        char status = scanner.next().toUpperCase().charAt(0);
        System.out.println("---------------------------------------------------------------");

        if(status == 'P'){
            if(attendence.markPresent(id)){
                System.out.println("        Attendance Marked Present");
                System.out.println("---------------------------------------------------------------");

            }else{
                System.out.println("        Attendance Not Marked. Try Again");
                System.out.println("---------------------------------------------------------------");

            }
        }else if(status == 'A'){
            if(attendence.markAbsent(id)){
                System.out.println("        Attendance Marked Absent");
                System.out.println("---------------------------------------------------------------");

            }else{
                System.out.println("        Attendance Not Marked. Try Again");
                System.out.println("---------------------------------------------------------------");
            }
        }else{
            System.out.println("        Invalid Input. Please only enter P or A");
            System.out.println("---------------------------------------------------------------");

        }
    }
    public static void generateAttendance(){
        System.out.println("---------------------------------------------------------------");
        System.out.println("                GENERATE ATTENDANCE REPORT");
        System.out.println("---------------------------------------------------------------");
        System.out.println();
        System.out.print("Enter Student ID: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        if(attendence.attendenceReport(id)){
            System.out.println("---------------------------------------------------------------");
            System.out.println("    Attendance Report Generated Successfully");
            System.out.println("---------------------------------------------------------------");

        }else{
            System.out.println("---------------------------------------------------------------");
            System.out.println("        Attendance Report Not Generated. Try Again");
            System.out.println("---------------------------------------------------------------");

        }
    }
    public static void removeStaff(){
        System.out.println("---------------------------------------------------------------");
        System.out.println("                        REMOVE STAFF");
        System.out.println("---------------------------------------------------------------");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("      Enter Username to remove: ");
        String username = scanner.next();
        System.out.print("      Enter Password to remove: ");
        String password = scanner.next();
        System.out.println("---------------------------------------------------------------");

        if(User.deleteUser(username, "Staff", password)){
            System.out.println("        Staff Removed Successfully");
            System.out.println("---------------------------------------------------------------");

        }else{
            System.out.println("        Staff Not Removed. Try Again");
            System.out.println("---------------------------------------------------------------");

        }
    }
    public static void removeStudent(){
        System.out.println("---------------------------------------------------------------");
        System.out.println("                        REMOVE STUDENT");
        System.out.println("---------------------------------------------------------------");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("      Enter Username to remove: ");
        String username = scanner.next();
        System.out.print("      Enter Password to remove: ");
        String password = scanner.next();
        System.out.println("---------------------------------------------------------------");
        if(User.deleteUser(username, "Student", password)){
            System.out.println("        Student Removed Successfully");
            System.out.println("---------------------------------------------------------------");
        }else{
            System.out.println("        Student Not Removed. Try Again");
            System.out.println("---------------------------------------------------------------");
        }
    }
    public static void getDataUser(){
        System.out.println("---------------------------------------------------------------");
        System.out.println("                        GET DATA");
        System.out.println("---------------------------------------------------------------");
        System.out.println();
        System.out.print("        Enter Username to get data: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        System.out.print("        Enter Role to get data: ");
        String role = scanner.next();
        System.out.println("----------------------------------------------------------------");
        System.out.println("    Username: " + User.getData(username, role).getUsername());
        System.out.println("    Name: " + User.getData(username, role).getName());
        System.out.println("    Role: " + User.getData(username, role).getRole());
        System.out.println("    ID: " + User.getData(username,role).getID());
        System.out.println("    Password: " + User.getData(username,role).getPassword());
        System.out.println("----------------------------------------------------------------");
        System.out.println("                    Retrived Data Successfully                  ");
        System.out.println("---------------------------------------------------------------");
        }
    public static void removeAttendence(){
        System.out.println("---------------------------------------------------------------");
        System.out.println("                       REMOVE ATTENDANCE");
        System.out.println("---------------------------------------------------------------");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("      Enter Student ID to remove Attendance: ");
        int id = scanner.nextInt();
        System.out.println("---------------------------------------------------------------");
        if(attendence.removeAttendance(id)){
            System.out.println("        Attendance Removed Successfully");
            System.out.println("---------------------------------------------------------------");
        }else{
            System.out.println("        Attendance Not Removed. Try Again");
            System.out.println("---------------------------------------------------------------");
        }
    }
    public static void coursesEnrolled(){
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("We are Working on it.... All features related to Course will be available soon");
        System.out.println("features like Marking Attendance via courses Enrolled by students etc...");
        System.out.println("Please check back later");
        System.out.println("---------------------------------------------------------------------------------");
    }
}
