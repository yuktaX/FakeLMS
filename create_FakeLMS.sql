create table Professor(
    Professor_ID char(5),
    Name varchar(20),
    Email varchar(30),
    constraint pk_Professor PRIMARY KEY (Professor_ID)
);

create table Course(
    Course_ID char(5),
    CourseName varchar(20),
    SemOfferedIn integer,
    Credits integer,
    Type varchar(20),
    Branch varchar(3),
    Professor_ID char(5),
    constraint pk_Course PRIMARY KEY (Course_ID)
);

create table Student(
    Student_ID char(5),
    Name varchar(20),
    Email varchar(20),
    Branch varchar(3),
    CurrentSemester integer,
    Gender char(1),
    DOB date,
    constraint pk_Student PRIMARY KEY (Student_ID)
);

create table Enrollment(
    Enrollment_ID char(5),
    Course_ID char(5),
    Student_ID char(5),
    Type varchar(20),
    constraint pk_Enrollment PRIMARY KEY (Enrollment_ID)
);

create table Performance(
    Performance_ID char(5),
    Course_ID char(5),
    Student_ID char(5),
    Grade varchar(2),
    Attendance float,
    constraint pk_Performance PRIMARY KEY (Performance_ID)
);

create table TA(
    Student_ID char(5),
    Course_ID char(5),
    constraint pk_TA PRIMARY KEY (Student_ID)
);