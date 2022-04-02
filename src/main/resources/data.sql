delete from User;
delete from Profesor;
delete from Kolegij;
delete from StudentKolegij;

INSERT INTO User(id,firstName, lastName, email, title, password) VALUES
(1,'Nikola', 'Matijevic','nmatijevi@tvz.hr', 'Student', 'a'),
(2,'Ivan', 'Kraljic', 'ikraljic@tvz.hr' ,'Student', 'a'),
(3,'Mario', 'Novacic', 'mnovacic@tvz.hr' ,'Student', 'a'),
(4,'Toni', 'Pavic', 'tpavic@tvz.hr' ,'Student', 'a'),
(5,'Ivan', 'Bagaric', 'ibagaric@tvz.hr' ,'Student', 'a'),
(6,'Miroslav', 'Slamic', 'mslamic@tvz.hr', 'Profesor', 'a'),
(7,'Tin', 'Kramberger', 'tkramberger@tvz.hr', 'Profesor', 'a'),
(8,'Ivan', 'Cesar','icesar@tvz.hr', 'Profesor', 'a'),
(9,'Aleksander', 'Radovan', 'aradovan@tvz.hr' ,'Profesor', 'a'),
(10,'Davor', 'Cafuta','dcafuta@tvz.hr', 'Profesor', 'a');

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

INSERT INTO authority(id, name)VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'DELETER');

INSERT INTO user_authority(user_id, authority_id)VALUES
(1,1),
(2,2),
(3,3);