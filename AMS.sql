USE attendence_db;

CREATE TABLE ADMIN ( ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50), ROLE VARCHAR(50), USERNAME VARCHAR(50) UNIQUE, PASSWORD VARCHAR(50) );
DESCRIBE ADMIN;

CREATE TABLE STAFF ( ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50), ROLE VARCHAR(50), USERNAME VARCHAR(50) UNIQUE, PASSWORD VARCHAR(50) );
CREATE TABLE STUDENT ( ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50), ROLE VARCHAR(50), USERNAME VARCHAR(50) UNIQUE, PASSWORD VARCHAR(50) );
DESCRIBE STAFF;
DESCRIBE STUDENT;
CREATE TABLE student_attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    present INT DEFAULT 0,
    absent INT DEFAULT 0,
    total_slots INT DEFAULT 0,
    FOREIGN KEY (STUDENT_ID) REFERENCES student(ID) ON DELETE CASCADE
);
CREATE TABLE staff_attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    staff_id INT NOT NULL,
    present INT DEFAULT 0,
    absent INT DEFAULT 0,
    total_slots INT DEFAULT 0,
    FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE CASCADE
)

SELECT * FROM ADMIN;
SELECT * FROM Student;
SELECT * FROM staff;

SELECT * FROM student_attendance WHERE student_id = 1;
select * From student where NAME = "Angath";

SELECT s.ID, s.NAME, sa.present, sa.absent, sa.total_slots
FROM STUDENT s
JOIN student_attendance sa ON s.ID = sa.student_id;

SELECT CONSTRAINT_NAME, TABLE_NAME, COLUMN_NAME, REFERENCED_TABLE_NAME, REFERENCED_COLUMN_NAME
FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'student_attendance';

SELECT * FROM student_attendance WHERE student_id = 1; -- Replace 1 with actual ID

INSERT INTO student_attendance (student_id, present, absent, total_slots) 
VALUES (1, 0, 0, 0) 
ON DUPLICATE KEY UPDATE student_id = student_id;

SELECT * FROM student_attendance WHERE student_id = 1;




