insert into Professor(Name, Email) values
('yoda', 'greenboi@jedi.com'),
('obi Wan Kenobi', 'obi@jedi.com'),
('anakin Skywalker', 'anakin@deathstar.com'),
('lord fartquad','lord@gmail.com'),
('shrek','bigboyshrek@gmail.com'),
('jolyne cujoh','jolyne@gmail.com'),
('peppa pig','peppapig@pigmail.com');

insert into Student(Name, Email, Branch, CurrentSemester, Gender, DOB)
values ('ahsoka', 'ahsoka@jedi.com', 'cse', 4, 'F', '2013-04-1'),
('auke', 'luke@tatooine.com', 'ece', 2, 'M', '2018-03-5'),
('leia', 'leia@tatooine.com', 'ece', 3, 'F', '2018-03-5'),
('vineet', 'vineet@gmail.com', 'cse', 5, 'M', '2003-04-22'),
('shawn','shawnmendez@gmail.com','cse',5,'M','2002-02-22'),
('karanjeet','karanjeet@gmail.com','cse',3,'M','2004-02-11'),
('shlok','shlokbaddy@gmail.com','cse',4,'M','2002-05-12'),
('rohini','rohinever@gmail.com','ece',2,'F','2005-02-01'),
('sofia','sofia@gmail.com','cse',3,'F','2003-02-09'),
('shekhar','thugshekhar@gmail.com','ece',1,'M','2004-02-04'),
('pranya','pranyewest@gmail.com','cse',1,'F','2004-02-06');

insert into Course(CourseName, SemOfferedIn, Credits, Type, Branch, Professor_ID)
values ('dsa', 2, 4, 'core','gen', 1),
('dbms', 4, 3, 'core', 'cse', 2),
('analog circuits', 4, 3, 'core', 'ece', 3),
('math 1',1,4,'core','gen',1),
('math 2',2,4,'core','gen',4),
('topology',4,4,'elective','gen', 5),
('number theory',4,4,'elective','gen', 6),
('daa',4,4,'core','cse', 7),
('signal system',3,4,'core','gen', 2),
('pocs',4,3,'core','ece', 3),
('operating system',4,4,'core','gen', 4),
('programming 1',1,4,'core','gen', 5),
('programming 2',2,4,'core','gen', 6),
('physics 1',3,3,'core','gen', 7),
('physics 2',4,4,'elective','gen', 1),
('ca',2,4,'core','gen',3),
('computer networks',2,3,'core','gen', 2);

insert into StudentLogin(Student_ID, Password)
values (1, 'password'),
(2, 'Password'),
(3, 'password'),
(4, 'password'),
(5, 'password'),
(6, 'password'),
(7, 'password'),
(8, 'password'),
(9, 'password'),
(10, 'password'),
(11, 'password');

insert into ProfessorLogin(Professor_ID, Password)
values (1, 'password'),
(2, 'Password'),
(3, 'password'),
(4, 'password'),
(5, 'password'),
(6, 'password'),
(7, 'password');

insert into TA(Student_ID,Course_ID)
values (10,3),
(3,1),
(2,7),
(4,11),
(5,3),
(6,6),
(7,8),
(8,9),
(9,10);
