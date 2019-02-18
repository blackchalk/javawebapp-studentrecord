DROP TABLE studentGrades IF EXISTS;
DROP TABLE studentEnrollments IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE students IF EXISTS;

CREATE TABLE students (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  middle_name VARCHAR(30)
    studentId VARCHAR(30),
  last_name  VARCHAR(30),
  address    VARCHAR(255),
    nationality    VARCHAR(30),
        gender    VARCHAR(30),
        primaryschool    VARCHAR(255),
        intermediate    VARCHAR(255),
        highschool    VARCHAR(255),
        date_of_admission    DATE,
        degree    VARCHAR(50),
        schoolyear VARCHAR(20),
        sy DATE,
        date_of_graduation DATE,
  dob       DATE);
CREATE INDEX students_last_name ON students (last_name);
CREATE INDEX students_studentID ON students (studentID);

CREATE TABLE studentEnrollments (
  id         INTEGER IDENTITY PRIMARY KEY,
  schoolTerm       VARCHAR(30),
  name VARCHAR(30),
  startDate DATE,
  endDate DATE,
  student_id   INTEGER NOT NULL
);
ALTER TABLE studentEnrollments ADD CONSTRAINT fk_studentEnrollments_students FOREIGN KEY (student_id) REFERENCES students (id);
CREATE INDEX studentEnrollments_schoolTerm ON studentEnrollments (schoolTerm);

CREATE TABLE studentGrades (
  id          INTEGER IDENTITY PRIMARY KEY,
  enrollment_id      INTEGER NOT NULL,
  courseId INTEGER,
  reexamgrade INTEGER,

  finalGrade INTEGER
);
ALTER TABLE studentGrades ADD CONSTRAINT fk_studentGrades_studentGrades FOREIGN KEY (studentGrade_id) REFERENCES studentGrades (id);
CREATE INDEX studentGrades_enrollment_id ON studentGrades (pet_id);
