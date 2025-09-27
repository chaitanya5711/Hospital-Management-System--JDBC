package HospitalManagementSystem;

import java.sql.*;

public class Doctor {
    private Connection connection;

    public Doctor(Connection connection) {
        this.connection = connection;
    }

    public void viewDoctors() {
        String query = "SELECT * FROM doctors";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("+----+----------+----------------+");
            System.out.println("| ID | Name     | Specialization |");
            System.out.println("+----+----------+----------------+");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String spec = rs.getString("specialization");
                System.out.printf("| %-2d | %-8s | %-14s |\n", id, name, spec);
            }

            System.out.println("+----+----------+----------------+");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id) {
        String query = "SELECT * FROM doctors WHERE id=?";
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
