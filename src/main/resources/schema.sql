--[1] DB 생성
drop database if exists springpy;
create database springpy;
use springpy;

--[2] User table 생성
drop table if exists user;
create table user (
    id int auto_increment,
    name varchar(20),
    age int,
    primary key (id)
);