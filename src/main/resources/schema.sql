create table if not exists User(
    id identity,
    firstName varchar(40) not null,
    lastName varchar(40) not null,
    email varchar(40) not null,
    title varchar(20) not null
);

create table if not exists Profesor(
    id identity,
    firstName varchar(40) not null,
    lastName varchar(40) not null,
    email varchar(40) not null,
    title varchar(20) not null
    );

create table if not exists  Kolegij(
    id identity,
    name varchar(60) not null
);

create table if not exists  StudentKolegij(
    student_id int(10) not null,
    kolegij_id int(10) not null,
    PRIMARY KEY (student_id, kolegij_id),
    foreign key (student_id) REFERENCES User (id),
    foreign key (kolegij_id) REFERENCES Kolegij (id)
);