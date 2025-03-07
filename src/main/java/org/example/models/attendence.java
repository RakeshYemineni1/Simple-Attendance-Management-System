package org.example.models;

import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.example.attendenceDb.Database;


public class attendence {
    public static boolean markPresent(int id) {
        String query = "UPDATE student_attendance SET present = present + 1 , total_slots = total_slots + 1 WHERE student_id = ?";
        try(Connection conn = Database.getConnection();
        PreparedStatement ps = conn.prepareStatement(query) ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean markAbsent(int id) {
        String query = "UPDATE student_attendance SET absent = absent + 1 , total_slots = total_slots + 1 WHERE student_id = ?";
        try(Connection conn = Database.getConnection();
        PreparedStatement ps = conn.prepareStatement(query) ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean attendenceReport(int id) {
        String query = "SELECT S.NAME, SA.PRESENT, SA.ABSENT, SA.TOTAL_SLOTS,  ROUND((sa.present / sa.total_slots) * 100, 2) AS attendance_percentage FROM STUDENT S JOIN student_attendance SA ON S.ID = SA.student_id WHERE S.id = ?";
        try {Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                 ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    System.out.println("              Student Attendance Report            ");
                    System.out.println("---------------------------------------------------");
                    System.out.println("    Name: " + rs.getString("NAME"));
                    System.out.println("    Present: " + rs.getInt("PRESENT"));
                    System.out.println("    Absent: " + rs.getInt("ABSENT"));
                    System.out.println("    Total Slots: " + rs.getInt("TOTAL_SLOTS"));
                    System.out.println("    Attendance Percentage: " + rs.getDouble("ATTENDANCE_PERCENTAGE") + "%");
                    return true;
                }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean removeAttendance(int id) {
        String query = "UPDATE student_attendance SET present = CASE WHEN present > 0 THEN  present - 1 ELSE present END, total_slots = CASE When total_slots > 0 THEN total_slots - 1 Else total_slots END WHERE id = ?";
        try(Connection conn = Database.getConnection();
        PreparedStatement ps = conn.prepareStatement(query) ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return false;
    }
}
