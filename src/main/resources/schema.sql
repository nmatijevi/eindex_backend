create table if not exists authority(
     id identity,
     name varchar(100) not null
);
create table if not exists User(
    id identity,
    firstName varchar(40) not null,
    lastName varchar(40) not null,
    username varchar(40) not null,
    email varchar(40) not null,
    title varchar(20) not null,
    password varchar(70) not null,
    auth integer not null
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
    studentid int(10) not null,
    kolegijid int(10) not null,
    ocjena int(10) not null,
    prijava boolean not null,
    foreign key (studentid) REFERENCES User (id),
    foreign key (kolegijid) REFERENCES Kolegij (id)
);



create table if not exists user_authority(
    user_id bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references user(id),
    constraint fk_authority foreign key(authority_id) references authority(id)
);