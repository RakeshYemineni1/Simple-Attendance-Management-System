Simple Attendance Management System




📌 Project Overview

The Simple Attendance Management System is a Java-based console application that helps educational institutions and organizations manage attendance records efficiently. It supports roles such as Admin, Staff, and Students with essential functionalities like user registration, attendance marking, and data retrieval.

🚀 Features

🔐 User Authentication (Admin, Staff, Student)

📋 Attendance Management (Mark Present/Absent)

📊 Student Attendance Tracking

🗄️ Database Integration (JDBC - MySQL)

🏷️ Role-Based Access Control

📁 Simple & Clean Code Structure

🛠️ Technologies Used

Java (Core Java, JDBC)

MySQL (Database for Attendance Storage)

Git & GitHub (Version Control)

Command-Line Interface (CLI)

🎯 Installation & Setup

1️⃣ Clone the Repository

 git clone https://github.com/RakeshYemineni1/Simple-Attendance-Management-System.git

2️⃣ Setup the Database (MySQL)

CREATE DATABASE attendance_system;
USE attendance_system;

CREATE TABLE STUDENT (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100),
    ROLE VARCHAR(50),
    USERNAME VARCHAR(100) UNIQUE,
    PASSWORD VARCHAR(255)
);

CREATE TABLE STUDENT_ATTENDANCE (
    student_id INT,
    present INT DEFAULT 0,
    absent INT DEFAULT 0,
    total_slots INT DEFAULT 0,
    FOREIGN KEY (student_id) REFERENCES STUDENT(ID)
);

3️⃣ Configure Database Connection

Edit the Database.java file and update your MySQL credentials:

private static final String URL = "jdbc:mysql://localhost:3306/attendance_system";
private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";

4️⃣ Run the Application

Compile and run the main file:

javac src/main/java/org/example/Main.java
java src/main/java/org/example/Main

📜 Usage

➤ Register a New User

Admin, Staff, or Student can register with unique usernames.

➤ Mark Attendance

Run the program and enter Student ID.

Choose Present (P) or Absent (A).

Attendance is updated in the database.

➤ View Attendance Records

Admin/Staff can fetch student attendance data.

📌 Future Enhancements

✔️ GUI Implementation using Java Swing or JavaFX✔️ Web-based version with Spring Boot & React✔️ Reports & Analytics for Attendance Insights

🤝 Contributing

Pull requests are welcome! Please follow these steps:

Fork the repository.

Create a new branch: git checkout -b feature-branch

Commit your changes: git commit -m "Added new feature"

Push to the branch: git push origin feature-branch

Submit a pull request 🚀

📜 License

This project is free to use and distributed under the MIT License.

💡 Author

👨‍💻 Rakesh Yemineni📧 Contact: [Your Email]🔗 GitHub: RakeshYemineni1

