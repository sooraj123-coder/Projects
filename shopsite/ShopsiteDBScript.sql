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

select * from product;