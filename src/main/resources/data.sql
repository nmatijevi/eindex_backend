delete from User;
delete from Profesor;
delete from Kolegij;
delete from StudentKolegij;
delete from user_authority;
delete from authority;

INSERT INTO User(id,firstName, lastName, username, email, title, password, auth) VALUES
(1,'Nikola', 'Matijevic', 'nmatijevi','nmatijevi@tvz.hr', 'Student', '$2b$10$lPXjqrhDJDgXP5r7l7D9XONAl07DuMVDd0HTG4Q1QewX/ks3/2NVS',1),
(2,'Ivan', 'Kraljic','ikraljic', 'ikraljic@tvz.hr' ,'Student', '$2a$10$ZJvFr/M1au/YBjLh0tgGn.lUgJI1VCRnUAOPurkholuzUXPxhIfzi',2),
(3,'Mario', 'Novacic','mnovacic', 'mnovacic@tvz.hr' ,'Student', '$2b$10$lPXjqrhDJDgXP5r7l7D9XONAl07DuMVDd0HTG4Q1QewX/ks3/2NVS',2),
(4,'Toni', 'Pavic', 'tpavic','tpavic@tvz.hr' ,'Student', '$2b$10$lPXjqrhDJDgXP5r7l7D9XONAl07DuMVDd0HTG4Q1QewX/ks3/2NVS',2),
(5,'Ivan', 'Bagaric', 'ibagaric','ibagaric@tvz.hr' ,'Student', '$2b$10$lPXjqrhDJDgXP5r7l7D9XONAl07DuMVDd0HTG4Q1QewX/ks3/2NVS',2),
(6,'Miroslav', 'Slamic','mslamic', 'mslamic@tvz.hr', 'Profesor', '$2b$10$lPXjqrhDJDgXP5r7l7D9XONAl07DuMVDd0HTG4Q1QewX/ks3/2NVS',2),
(7,'Tin', 'Kramberger','tkramberger', 'tkramberger@tvz.hr', 'Profesor', '$2b$10$lPXjqrhDJDgXP5r7l7D9XONAl07DuMVDd0HTG4Q1QewX/ks3/2NVS',2),
(8,'Ivan', 'Cesar','icesar','icesar@tvz.hr', 'Profesor', '$2b$10$lPXjqrhDJDgXP5r7l7D9XONAl07DuMVDd0HTG4Q1QewX/ks3/2NVS',2),
(9,'Aleksander', 'Radovan','aradovan', 'aradovan@tvz.hr' ,'Profesor', '$2b$10$lPXjqrhDJDgXP5r7l7D9XONAl07DuMVDd0HTG4Q1QewX/ks3/2NVS',2),
(10,'Davor', 'Cafuta','dcafuta','dcafuta@tvz.hr', 'Profesor', '$2b$10$lPXjqrhDJDgXP5r7l7D9XONAl07DuMVDd0HTG4Q1QewX/ks3/2NVS',2);

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
(5,'Programiranje u Javi'),
(6, 'Kineziološka kultura'),
(7, 'Matematika 2');

INSERT INTO StudentKolegij(studentid, kolegijid, ocjena, prijava) VALUES
(1,1,0, FALSE),
(1,5,0, FALSE),
(2,1,0, FALSE),
(2,2,0, FALSE),
(3,3,0, FALSE),
(4,4,0, FALSE),
(5,5,0, FALSE);

INSERT INTO authority(id, name) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'DELETER');

INSERT INTO user_authority(user_id, authority_id)VALUES
(1,1),
(2,2),
(3,2),
(4,2),
(5,2),
(6,2),
(7,2),
(8,2),
(9,2),
(10,2);