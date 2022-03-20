create table if not exists Users(
    id identity,
    firstName varchar(40) not null,
    lastName varchar(40) not null,
    title varchar(20) not null
);