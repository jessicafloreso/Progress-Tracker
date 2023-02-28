drop database if exists pokemon_tracker;

create database pokemon_tracker;
use pokemon_tracker;

-- create table pokemon
-- (
-- 	id int primary key,
--     name varchar(30) unique,
--     type_1 varchar(30),
--     type_2  varchar(30),
--     total int,
--     hp int,
--     attack int,
--     defense int,
--     sp_atk int,
--     sp_def int,
--     speed int,
--     legendary boolean
--     # other intrinsic stats
-- );

create table pokemon
(
	id int primary key,
    name varchar(30) unique
    # other intrinsic stats
);

create table users
(
	id int primary key auto_increment,
	username varchar(30) unique,
    password varchar(30)
);

create table collected # pokemon_users (seperate tables for not collected, in progress, collected?)
(
    user_name varchar(30),
    pokemon_name varchar(30), # or name
    level int,
    completed boolean,
    primary key (user_name, pokemon_name),
    foreign key (user_name) references users(username),
    foreign key (pokemon_name) references pokemon(name)
);

insert into pokemon(id, name)
	values(1, 'Bulbasaur');
insert into pokemon(id, name)
	values(2, 'Ivysaur'); 
insert into pokemon(id, name)
	values(3, 'Venusaur'); 
insert into pokemon(id, name)
	values(4, 'Charmander'); 
insert into pokemon(id, name)
	values(5, 'Charmeleon'); 
insert into pokemon(id, name)
	values(6, 'Charizard'); 
insert into pokemon(id, name)
	values(7, 'Squirtle'); 
insert into pokemon(id, name)
	values(8, 'Wartortle'); 
insert into pokemon(id, name)
	values(9, 'Blastoise'); 
insert into pokemon(id, name)
	values(25, 'Pikachu'); 

insert into users(username, password)
	values('username', 'password'); 
insert into users(username, password)
	values('username2', 'password'); 
insert into users(username, password)
	values('username3', 'password'); 
insert into users(username, password)
	values('username4', 'password'); 











-- create table favorited (
--     user_name varchar(30),
--     pokemon_name varchar(30),
--     primary key (user_name, pokemon_name),
--     foreign key (user_name) references users(username),
--     foreign key (pokemon_name) references pokemon(name)
-- );

-- create table unobtainable (
--     user_name varchar(30),
--     pokemon_name varchar(30),
--     primary key (user_name, pokemon_name),
--     foreign key (user_name) references users(username),
--     foreign key (pokemon_name) references pokemon(name)
-- );

