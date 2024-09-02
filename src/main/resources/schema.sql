----[1] DB 생성
--drop database if exists springpy;
--create database springpy;
--use springpy;
--
----[2] User table 생성
--drop table if exists user;
--create table user (
--    id int auto_increment,
--    name varchar(20),
--    age int,
--    primary key (id)
--);

--task1
-- [1] DB create
drop database if exists accounttask;
create database accounttask;
use accounttask;

-- [2] Account table create
drop table if exists account;
create table account (
    id int auto_increment,
    description varchar(255) not null,
    amount int not null,
    record_date date not null,
    primary key (id)
);