create table Professor(
    Professor_ID integer AUTO_INCREMENT,
    Name varchar(20),
    Email varchar(100),
    constraint pk_Professor PRIMARY KEY (Professor_ID)
);

create table Course(
    Course_ID integer AUTO_INCREMENT,
    CourseName varchar(30),
    SemOfferedIn integer,
    Credits integer,
    Type varchar(20),
    Branch varchar(3),
    Professor_ID integer,
    constraint pk_Course PRIMARY KEY (Course_ID)
);

create table Student(
    Student_ID int AUTO_INCREMENT,
    Name varchar(20),
    Email varchar(100),
    Branch varchar(3),
    CurrentSemester integer,
    Gender char(1),
    DOB date,
    constraint pk_Student PRIMARY KEY (Student_ID)
);

create table Enrollment(
    Enrollment_ID integer AUTO_INCREMENT,
    Course_ID integer,
    Student_ID integer,
    constraint pk_Enrollment PRIMARY KEY (Enrollment_ID)
);

create table Performance(
    Performance_ID integer AUTO_INCREMENT,
    Course_ID integer,
    Student_ID integer,
    Grade varchar(2),
    Attendance float,
    constraint pk_Performance PRIMARY KEY (Performance_ID)
);

create table TA(
    Student_ID integer,
    Course_ID integer,
    constraint pk_TA PRIMARY KEY (Student_ID)
);

create table StudentLogin(
    Login_ID integer AUTO_INCREMENT,
    Student_ID integer,
    Password varchar(30),
    constraint pk_Slogin PRIMARY KEY (Login_ID)
);

create table ProfessorLogin(
    Login_ID integer AUTO_INCREMENT,
    Professor_ID integer,
    Password varchar(30),
    constraint pk_Plogin PRIMARY KEY (Login_ID)
);