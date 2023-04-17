insert into Professor(Name, Email) values
('Yoda', 'greenboi@jedi.com'),
('Obi Wan Kenobi', 'obi@jedi.com'),
('Anakin Skywalker', 'anakin@deathstar.com');

insert into Student(Name, Email, Branch, CurrentSemester, Gender, DOB)
values ('Ahsoka', 'ahsoka@jedi.com', 'CSE', 4, 'F', '2013-04-1'),
('Luke', 'luke@tatooine.com', 'ECE', 2, 'M', '2018-03-5'),
('Leia', 'leia@tatooine.com', 'ECE', 3, 'F', '2018-03-5'),
('Vineet', 'vineet@gmail.com', 'CSE', 5, 'M', '2003-04-22'),
('Shawn','shawnmendez@gmail.com','CSE',5,'M','2002-02-22'),
('Karanjeet','karanjeet@gmail.com','CSE',3,'M','2004-02-11'),
('Shlok','shlokbaddy@gmail.com','CSE',4,'M','2002-05-12'),
('Rohini','rohinever@gmail.com','ECE',2,'F','2005-02-01'),
('Sofia','sofia@gmail.com','CSE',3,'F','2003-02-09'),
('Shekhar','thugshekhar@gmail.com','ECE',1,'M','2004-02-04'),
('Pranya','pranyewest@gmail.com','CSE',1,'F','2004-02-06');

insert into Course(CourseName, SemOfferedIn, Credits, Type, Branch, Professor_ID)
values ('DSA', 2, 4, 'core', NULL, 1),
('DBMS', 4, 3, 'core', 'CSE', 2),
('Analog Circuits', 4, 3, 'core', 'ECE', 3);
