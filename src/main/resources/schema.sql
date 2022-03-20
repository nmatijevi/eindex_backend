create table if not exists Student(
    id identity,
    firstName varchar(40) not null,
    lastName varchar(40) not null,
    title varchar(20) not null
);

create table if not exists Profesor(
    id identity,
    firstName varchar(40) not null,
    lastName varchar(40) not null,
    email varchar(40) not null,
    title varchar(20) not null
);