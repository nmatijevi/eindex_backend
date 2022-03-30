delete from User;
delete from Profesor;
delete from Kolegij;
delete from StudentKolegij;

INSERT INTO User(id,firstName, lastName, email, title) VALUES
(1,'Nikola', 'Matijevic','nmatijevi@tvz.hr', 'Student'),
(2,'Ivan', 'Kraljic', 'ikraljic@tvz.hr' ,'Student'),
(3,'Mario', 'Novacic', 'mnovacic@tvz.hr' ,'Student'),
(4,'Toni', 'Pavic', 'tpavic@tvz.hr' ,'Student'),
(5,'Ivan', 'Bagaric', 'ibagaric@tvz.hr' ,'Student'),
(6,'Miroslav', 'Slamic', 'mslamic@tvz.hr', 'Profesor'),
(7,'Tin', 'Kramberger', 'tkramberger@tvz.hr', 'Profesor'),
(8,'Ivan', 'Cesar','icesar@tvz.hr', 'Profesor'),
(9,'Aleksander', 'Radovan', 'aradovan@tvz.hr' ,'Profesor'),
(10,'Davor', 'Cafuta','dcafuta@tvz.hr', 'Profesor');

INSERT INTO Profesor(id,firstName, lastName, email, title) VALUES
(1,'Miroslav', 'Slamic', 'mslamic@tvz.hr', 'prof'),
(2,'Tin', 'Kramberger', 'tkramberger@tvz.hr', 'prof'),
(3,'Ivan', 'Cesar','icesar@tvz.hr', 'Prof'),
(4,'Aleksander', 'Radovan', 'aradovan@tvz.hr' ,'Prof'),
(5,'Davor', 'Cafuta','dcafuta@tvz.hr', 'Prof');

INSERT INTO Kolegij(id,name) VALUES
(1,'Programsko inzenjerstvo'),
(2,'Matematika'),
(3,'Web aplikacije u Javo'),
(4,'Objektno orijentirano programiranej'),
(5,'Programiranje u Javi');

INSERT INTO StudentKolegij(student_id, kolegij_id) VALUES
(1,1),
(1,2),
(2,1),
(2,2),
(3,3),
(4,4),
(5,5);