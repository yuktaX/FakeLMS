-- ALTER TABLE Professor
-- ADD CONSTRAINT fk_reg_patientID 
-- FOREIGN KEY (patientID) REFERENCES Patient(patientID);

ALTER TABLE Course
ADD CONSTRAINT fk_course_professorID
FOREIGN KEY (Professor_ID) REFERENCES Professor(Professor_ID);

-- ALTER TABLE Student
-- ADD CONSTRAINT fk_diagnostic_visitID
-- FOREIGN KEY (diagnosticTestID) REFERENCES Visit(visitID);

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



