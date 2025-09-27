package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();

        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        System.out.print("Enter patient gender: ");
        String gender = scanner.nextLine();

        try {
            String query = "INSERT INTO patients(name, age, gender) VALUES(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);

            if (ps.executeUpdate() > 0) {
                System.out.println("Patient added successfully!");
            } else {
                System.out.println("Failed to add patient.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewPatients() {
        String query = "SELECT * FROM patients";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("+----+---------------------+-----+--------+");
            System.out.println("| ID | Name                | Age | Gender |");
            System.out.println("+----+---------------------+-----+--------+");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                System.out.printf("| %-2d | %-19s | %-3d | %-6s |\n", id, name, age, gender);
            }

            System.out.println("+----+---------------------+-----+--------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
