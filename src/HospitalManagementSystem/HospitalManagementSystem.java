package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "Chai@27#";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);

            while (true) {
                System.out.println("\nHOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // clear buffer

                switch (choice) {
                    case 1:
                        patient.addPatient();
                        break;
                    case 2:
                        patient.viewPatients();
                        break;
                    case 3:
                        doctor.viewDoctors();
                        break;
                    case 4:
                        bookAppointment(patient, doctor, connection, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting system...");
                        connection.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner) {
        System.out.println("Available Doctors:");
        doctor.viewDoctors(); // show list of doctors

        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        System.out.print("Enter Doctor ID from above list: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.nextLine();

        try {
            if (patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)) {
                if (checkDoctorAvailability(doctorId, appointmentDate, connection)) {
                    String query = "INSERT INTO appointment(patient_id, doctor_id, appointment_date) VALUES(?,?,?)";
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setInt(1, patientId);
                    ps.setInt(2, doctorId);
                    ps.setString(3, appointmentDate);

                    if (ps.executeUpdate() > 0) {
                        System.out.println("Appointment booked successfully!");
                    } else {
                        System.out.println("Failed to book appointment.");
                    }
                } else {
                    System.out.println("Doctor is not available on this date!");
                }
            } else {
                System.out.println("Patient or Doctor does not exist!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String date, Connection connection) {
        String query = "SELECT * FROM appointment WHERE doctor_id=? AND appointment_date=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, doctorId);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();
            return !rs.next(); // true if doctor is free
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
