drop database if exists pokemon_tracker;

create database pokemon_tracker;
use pokemon_tracker;

create table pokemon
(
    name varchar(30) primary key,
    id int
    # other intrinsic stats
);

create table users
(
	username varchar(30) primary key,
    password varchar(30),
	id int,
    name varchar(30)
);

create table collected # pokemon_users (seperate tables for not collected, in progress, collected?)
(
	id int primary key,
    user_name varchar(30),
    pokemon_name varchar(30), # or name
    level int,
    compleated boolean,
    foreign key (user_name) references users(username),
    foreign key (pokemon_name) references pokemon(name)
);

create table favorited (
	id int primary key,
    user_name varchar(30),
    pokemon_name varchar(30),
    foreign key (user_name) references users(username),
    foreign key (pokemon_name) references pokemon(name)
);

create table unobtainable (
	id int primary key,
    user_name varchar(30),
    pokemon_name varchar(30),
    foreign key (user_name) references users(username),
    foreign key (pokemon_name) references pokemon(name)
);