package org.example.attendenceDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class Database{
    private static final String URL = "jdbc:mysql://localhost:3306/attendence_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Ajantha@123";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC Driver is not Installed");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

