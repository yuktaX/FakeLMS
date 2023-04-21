insert into Professor(Name, Email) values
('yoda', 'greenboi@jedi.com'),
('obi Wan Kenobi', 'obi@jedi.com'),
('anakin Skywalker', 'anakin@deathstar.com'),
('lord fartquad','lord@gmail.com'),
('shrek','bigboyshrek@gmail.com'),
('jolyne cujoh','jolyne@gmail.com'),
('peppa pig','peppapig@pigmail.com');

insert into Student(Name, Email, Branch, CurrentSemester, Gender, DOB)
values ('ahsoka', 'ahsoka@jedi.com', 'CSE', 4, 'F', '2013-04-1'),
('auke', 'luke@tatooine.com', 'ECE', 2, 'M', '2018-03-5'),
('leia', 'leia@tatooine.com', 'ECE', 3, 'F', '2018-03-5'),
('vineet', 'vineet@gmail.com', 'CSE', 5, 'M', '2003-04-22'),
('shawn','shawnmendez@gmail.com','CSE',5,'M','2002-02-22'),
('karanjeet','karanjeet@gmail.com','CSE',3,'M','2004-02-11'),
('shlok','shlokbaddy@gmail.com','CSE',4,'M','2002-05-12'),
('rohini','rohinever@gmail.com','ECE',2,'F','2005-02-01'),
('sofia','sofia@gmail.com','CSE',3,'F','2003-02-09'),
('shekhar','thugshekhar@gmail.com','ECE',1,'M','2004-02-04'),
('pranya','pranyewest@gmail.com','CSE',1,'F','2004-02-06');

insert into Course(CourseName, SemOfferedIn, Credits, Type, Branch, Professor_ID)
values ('DSA', 2, 4, 'core', NULL, 1),
('DBMS', 4, 3, 'core', 'CSE', 2),
('Analog Circuits', 4, 3, 'core', 'ECE', 3);
