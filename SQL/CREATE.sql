create database UniversityDB;

use UniversityDB;

CREATE TABLE Person (
                        PersonID INT PRIMARY KEY IDENTITY(1,1),
                        FirstName VARCHAR(50) NOT NULL,
                        LastName VARCHAR(50) NOT NULL,
                        Address VARCHAR(255) not null,
                        Gender VARCHAR(10)not null,
                        Birthdate DATE not null --commit1
);

CREATE TABLE Student (
                         StudentID INT PRIMARY KEY,
                         UniversityID int NOT NULL,
                         FOREIGN KEY (StudentID) REFERENCES Person(PersonID)
);
CREATE TABLE Course (
                        CourseID INT PRIMARY KEY IDENTITY(1,1),
                        CourseName VARCHAR(100) NOT NULL,
                        CreditHours INT NOT NULL
);

CREATE TABLE Professor (
                           ProfID INT PRIMARY KEY,
                           Salary Money NOT NULL,
                           CourseID INT NOT NULL,
                           FOREIGN KEY (CourseID) REFERENCES Course(CourseID)
);



CREATE TABLE Phone (
                       PhoneID INT PRIMARY KEY IDENTITY(1,1),
                       Phone VARCHAR(20) NOT NULL,
                       PersonID INT NOT NULL,
                       FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
);
CREATE TABLE Login (
                       LoginID INT PRIMARY KEY IDENTITY(1,1),
                       Email VARCHAR(255) UNIQUE NOT NULL,
                       Password VARCHAR(65) NOT NULL,
                       PersonID INT NOT NULL,
                       FOREIGN KEY (PersonID) REFERENCES Person(PersonID)
);

CREATE TABLE Enrollment (
                            EnrollmentID INT PRIMARY KEY IDENTITY(1,1),
                            GPA_Value DECIMAL(3,2),
                            GPA_Percentage DECIMAL(5,2),
                            EnrollDate DATE NOT NULL,
                            CourseID INT NOT NULL,
                            StudentID INT NOT NULL,
                            FOREIGN KEY (CourseID)REFERENCES Course(CourseID),
                            FOREIGN KEY (StudentID) REFERENCES Student(StudentID)
);