ALTER TABLE Course
ADD CONSTRAINT fk_course_professorID
FOREIGN KEY (Professor_ID) REFERENCES Professor(Professor_ID);

ALTER TABLE Performance
ADD CONSTRAINT fk_performance_studentID
FOREIGN KEY (Student_ID) REFERENCES Student(Student_ID);

ALTER TABLE Performance
ADD CONSTRAINT fk_performance_courseID
FOREIGN KEY (Course_ID) REFERENCES Course(Course_ID);

ALTER TABLE Enrollment
ADD CONSTRAINT fk_enrollment_studentID
FOREIGN KEY (Student_ID) REFERENCES Student(Student_ID);

ALTER TABLE Enrollment
ADD CONSTRAINT fk_enrollment_courseID
FOREIGN KEY (Course_ID) REFERENCES Course(Course_ID);

ALTER TABLE TA
ADD CONSTRAINT fk_ta_studentID
FOREIGN KEY (Course_ID) REFERENCES Course(Course_ID);

ALTER TABLE StudentLogin
ADD CONSTRAINT fk_StudentLogin_studentID
FOREIGN KEY (Student_ID) REFERENCES Student(Student_ID);

ALTER TABLE ProfessorLogin
ADD CONSTRAINT fk_ProfessorLogin_professorID
FOREIGN KEY (Professor_ID) REFERENCES Professor(Professor_ID);