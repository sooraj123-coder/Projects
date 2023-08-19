create database shopsite;

use shopsite;

create table category(
cid int auto_increment,
cname varchar(50) unique,
primary key(cid)
);

select * from category;

create table product(
pid int auto_increment,
pname varchar(100) not null,
pprice double not null,
pweight double ,
pdescription varchar(500),
pimagename varchar(200),
primary key(pid),
cid int not null,
CONSTRAINT `products_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
);

#creating authentication tables for the website
create table users(
id int auto_increment,
email varchar(100) not null,
fname varchar(50),
lname varchar(50),
password varchar(100) not null,
active tinyint,
primary key(id),
unique key(email)
);
insert into users(email,fname,lname,password) values("soorajmailme@gmail.com","sooraj","chaudhary","s123");

create table roles(
id int auto_increment primary key,
email varchar(100),
role varchar(50) not null,
CONSTRAINT `user_ibfk_1` FOREIGN KEY (`email`) REFERENCES `users` (`email`)
);
insert into roles(email,role) values("soorajmailme@gmail.com","Admin");

