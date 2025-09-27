# 🏥 Hospital Management System

A **Java & MySQL based Hospital Management System** for managing patients, doctors, and appointments.  
This project demonstrates the use of **JDBC, MySQL, and Object-Oriented Programming (OOP)** concepts.

---

## 📖 Project Description
The **Hospital Management System** is a console-based application that helps manage hospital data efficiently.  
It allows the hospital staff to register patients, add doctors with their specialization, and book appointments.  
The system ensures that doctors cannot be double-booked on the same date and maintains patient and doctor records securely in a **MySQL database**.

This project was created to practice:
- **Java programming (Core + JDBC)**
- **SQL database integration**
- **Object-Oriented Programming**
- **Error handling & validation**

---

## ✨ Features
- Add new patients with details (Name, Age, Gender)
- View all patients
- Add doctors with specialization
- View all doctors
- Book appointments between patients and doctors
- Prevents double booking of doctors on the same date
- Stores data persistently in MySQL database

---

## 🛠️ Tech Stack
- **Java** (Core + JDBC)
- **MySQL** (Database)
- **JDBC Driver** (`com.mysql.cj.jdbc.Driver`)
- **Object-Oriented Programming (OOP)**

---

## 📂 Database Schema
```sql
CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(50) NOT NULL
);

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL
);

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE ON UPDATE CASCADE
);
🚀 How to Run

Clone this repository:

git clone https://github.com/your-username/hospital-management-system.git


Open the project in your IDE (Eclipse/IntelliJ/VS Code).

Configure MySQL connection in HospitalManagementSystem.java:

private static final String url = "jdbc:mysql://localhost:3306/hospital";
private static final String username = "root";
private static final String password = "your_password";


Run the main file:

HospitalManagementSystem.java

📸 Sample Menu
HOSPITAL MANAGEMENT SYSTEM
1. Add Patient
2. View Patients
3. Add Doctor
4. View Doctors
5. Book Appointment
6. Exit
