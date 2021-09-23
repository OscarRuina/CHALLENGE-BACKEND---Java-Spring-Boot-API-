drop database if exists `db-challenge`;
create database if not exists `db-challenge`;

insert into roles(name) values('ROLE_USER');
insert into roles(name) values('ROLE_ADMIN');