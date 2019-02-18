CREATE DATABASE IF NOT EXISTS studentrecorddb;

ALTER DATABASE studentrecorddb
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON studentrecorddb.* TO pc@localhost IDENTIFIED BY 'pc';

USE studentrecorddb;

CREATE TABLE IF NOT EXISTS students (
  id        INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  studentId VARCHAR(30),
  first_name VARCHAR(30),
  middle_name VARCHAR(30),
  last_name  VARCHAR(30),
  address    VARCHAR(255),
    nationality    VARCHAR(30),
        gender    VARCHAR(30),
        primaryschool    VARCHAR(255), 
    intermediate    VARCHAR(255),
        highschool    VARCHAR(255),
        date_of_admission    DATE,
        degree    VARCHAR(50),
        so_no VARCHAR(20),
        schoolyear DATE,
        date_of_graduation DATE,
          dob       DATE,
          INDEX(last_name)
)engine=InnoDB;


CREATE TABLE IF NOT EXISTS studentEnrollments (
  id         INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  schoolTerm       VARCHAR(30),
  startDate DATE,
  endDate DATE,
    INDEX(name),
  student_id INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS studentGrades (
  id  INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  enrollment_id       INT(4) UNSIGNED NOT NULL,
  courseId  INT(4) UNSIGNED NOT NULL,
  reexamgrade  INT(4) UNSIGNED NOT NULL,

  finalGrade  INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (enrollment_id) REFERENCES studentEnrollments(id)
)engine=InnoDB;

