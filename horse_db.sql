drop table if exists horse;
drop table if exists horseman;
drop table if exists horse_horseman;
drop table if exists user;
drop table if exists post;

create table horse(
	id int not null auto_increment, 
	horseName varchar(40), 
	PRIMARY KEY (id)
);

create table horseman(
	id int not null auto_increment, 
	horsemanName varchar(40), 
	primary key (id)
);

create table horse_horseman(
	id int not null auto_increment, 
	horseId int, horsemanId int, 
	day varchar(40), 
	place varchar(255), 
	primary key (id)
);

create table user(
	id int not null auto_increment,
	username varchar(40) not null,
	password varchar(255) not null,
	primary key(id)
);

create table post(
	id int not null auto_increment,
	title varchar(55) not null,
	body varchar(10000) not null,
	userId int not null,
	primary key(id)
);

insert into horse (horseName) values ('Fury');
insert into horse (horseName) values ('Emmily');
insert into horseman (horsemanName) values ('Eva');
insert into horseman (horsemanName) values ('Manu');
insert into horseman (horsemanName) values ('Jens');
insert into horse_horseman (horseId, horsemanId, day, place) values (2,1,"20.12.2001", "Paris");
insert into horse_horseman (horseId, horsemanId, day, place) values (1,1,"30.11.2000", "Vienna");
insert into horse_horseman (horseId, horsemanId, day, place) values (1,3,"11.11.2011", "Munich");
insert into horse_horseman (horseId, horsemanId, day, place) values (2,2,"01.09.1999", "Lyon");
