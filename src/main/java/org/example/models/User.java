package org.example.models;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.attendenceDb.Database;

public class User {
    private String name;
    private String role;
    private String username;
    private String password;
    private int ID;
    private static final String KEY = "04102002";

    public User(){
    }// Constant for Admin key

    public User(String name, String role, String username, String password) {
        this.name = name;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    // Setter for name
    void setName(String name) {
        this.name = name;
    }

    // Setter for role
    void setRole(String role) {
        this.role = role;
    }

    // Method to handle user role logic and create user profiles
    public void getRole(String role) {
        try (Scanner sc = new Scanner(System.in)) {  // Use try-with-resources to ensure the scanner is closed
            if (role.equals("Admin")) {
                createAdminProfile(sc);  // Pass the scanner to the createAdminProfile method
            } else if (role.equals("Staff")) {
                createUserProfile(sc);  // Pass the scanner to the createUserProfile method
                System.out.println("Staff Profile has been created...");
            } else if (role.equals("Student")) {
                createUserProfile(sc);  // Pass the scanner to the createUserProfile method
                System.out.println("Student Profile has been created...");
            } else {
                System.out.println("Invalid role!");
            }
        }
    }

    // Method to create Admin profile
    private void createAdminProfile(Scanner sc) {
        System.out.print("       ~ Enter Admin key for Creating an admin profile: ");
        System.out.println();
        String key = sc.next();
        if (key.equals(KEY)) {
            System.out.println("        Verified! Creating a new Admin Profile....");
            System.out.println();
            System.out.print("        Enter Name: ");
            name = sc.next();
            System.out.println();
            System.out.print("        Enter User Name: ");
            username = sc.next();
            System.out.println();
            System.out.print("Enter Password: ");
            password = sc.next();
            System.out.println();
            while (!validPass(password)) {
                System.out.println("        The password should be between 8 and 12 characters.");
                System.out.println();
                System.out.print("        Enter Password: ");
                password = sc.next();
                System.out.println();
            }
            if(insertData("Admin")){
             System.out.println("        Admin Profile has been created...");
            } else {
                System.out.println("        Failed to create Admin Profile...");
            }
        } else {
            System.out.println("        Invalid Key! Please try again");
        }
    }

    // Method to create Staff or Student profile
    private void createUserProfile(Scanner sc) {
        System.out.println("        Enter Name: ");
        name = sc.next();
        System.out.println("        Enter User Name: ");
        username = sc.next();
        System.out.println("        Enter Password: ");
        password = sc.next();
        while (!validPass(password)) {
            System.out.println("        The password should be between 8 and 12 characters.");
            System.out.println();
            System.out.print("        Enter Password: ");
            password = sc.next();
            System.out.println();
        }
        if(insertData(role)){
            System.out.println("        " + role + " Profile has been created...");
        } else {
             System.out.println("        Failed to create " + role + " Profile...");
        }
    }

    public Boolean insertData(String role) {
        String query = "";
        String attQuery = null;
        if (role.equals("Admin")) {
            query = "INSERT INTO ADMIN (NAME, ROLE, USERNAME, PASSWORD) VALUES (?, ?, ?, ?)";
        } else if (role.equals("Staff")) {
            query = "INSERT INTO STAFF (NAME, ROLE, USERNAME, PASSWORD) VALUES (?, ?, ?, ?)";
        } else if (role.equals("Student")) {
            query = "INSERT INTO STUDENT (NAME, ROLE, USERNAME, PASSWORD) VALUES (?, ?, ?, ?)";
            attQuery = "INSERT INTO STUDENT_ATTENDANCE (student_id, present, absent, total_slots) VALUES (?, 0, 0, 0)";
        } else {
            System.out.println("Invalid role!");
            return false;
        }

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            ps.setString(1, name);
            ps.setString(2, role);
            ps.setString(3, username);
            ps.setString(4, password);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("    Profile has been created...");
            } else {
                System.out.println("    Failed to create profile");
                conn.rollback();
                return false;
            }
            if(role.equals("Student") && attQuery != null) {
                try(ResultSet rs = ps.getGeneratedKeys()){
                    if (rs.next()) {
                        int Student_id = rs.getInt(1);
                        try (PreparedStatement attps = conn.prepareStatement(attQuery)) {
                            attps.setInt(1, Student_id);
                            int attRowUpdate = attps.executeUpdate();
                            if (attRowUpdate > 0) {
                                System.out.println("    Student Attendance has been created...");
                            }else{
                                System.out.println("    Failed to create Student Attendance");
                                conn.rollback();
                                return false;
                            }
                        }
                    }
                }
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("    Failed to create profile");
        }
        return false;
    }

    public static User getData(String username, String role){
        String query = "";
        if(role.equals("Admin") || role.equals("Staff") || role.equals("Student")){
            query = "SELECT * FROM " + role + " WHERE USERNAME = ?";
        }else{
            System.out.println("Invalid role!");
            return null;
        }
        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();

             if(resultSet.next()){
                 String name = resultSet.getString("NAME");
                 String password = resultSet.getString("PASSWORD");
                 int ID = resultSet.getInt("ID");
                 User user = new User(name, role);
                 user.username = username;
                 user.password = password;
                 user.ID = ID;
                 return user;
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User Authenticate(String username, String password, String role){
           String query = "SELECT * FROM " + role + " WHERE USERNAME = ? AND PASSWORD = ?";
           try(Connection conn = Database.getConnection();
           PreparedStatement ps = conn.prepareStatement(query)){
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet resultSet = ps.executeQuery();

                if(resultSet.next()){
                   String name = resultSet.getString("NAME");
                   int id = resultSet.getInt("ID");
                   String usernameDB = resultSet.getString("USERNAME");
                    String passwordDb = resultSet.getString("PASSWORD");
                    String roleDb = resultSet.getString("ROLE");
                    User user = new User(name, roleDb, usernameDB, passwordDb);
                    return user;
                }
           } catch (SQLException e) {
               System.out.println("..............Invalid Credentials...............");
           }
           return null;
    }

    public static boolean deleteUser(String username, String role, String password){
        String query = "DELETE FROM " + role + " WHERE USERNAME = ? AND PASSWORD = ?";
        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getID() {
        return ID;
    }
    // Password validation method
    public boolean validPass(String password) {
        return password.length() >= 8 && password.length() <= 12;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for role
    public String getRole() {
        return role;
    }
}


