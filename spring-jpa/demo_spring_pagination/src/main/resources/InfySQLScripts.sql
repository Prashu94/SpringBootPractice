-- create database infy_db;
use infy_db;
drop table if exists customer;
create table customer(
phone_no bigint primary key,
name varchar(50),
age integer,
gender char(10),
address varchar(50),
plan_id integer
);

delete from customer;

select * from customer; -- 7022713722


-- create database customer_db;
use customer_db;

drop table card;
drop table customer;
create table customer (
  customer_id int auto_increment not null ,
  emailid varchar(20),
  name varchar(20),
  date_of_birth date, 
  customer_type varchar(10),
  constraint customer_pk primary key (customer_id)
);

insert into customer values (1001,'steven@infy.com', 'Steven Martin', sysdate()-interval 7476 day, 'GOLD');
insert into customer values (1002,'kevin@infy.com', 'Kevin Nelson', sysdate()-interval 11374 day, 'SILVER');
insert into customer values (1003,'john@infy.com', 'John Matric',sysdate()-interval 12344 day, 'PLATINUM');
insert into customer values (1004,'chan@infy.com', 'Chan Mathew', sysdate()-interval 10344 day, 'SILVER');
insert into customer values (1005,'jill@infy.com', 'Jill Mathew', sysdate()-interval 11374 day, 'GOLD');

create table card (
  card_id int not null,
  card_number varchar(20),
  expiry_date date,
  cust_id int,
  constraint card_pk primary key (card_id),
  constraint fk_card_cust foreign key (cust_id) references customer(customer_id)
);



insert into card values(12346, '6642160005012193',sysdate()+interval 3400 day,1001);
insert into card values(12347, '6642160005012194',sysdate()+interval 4560 day,1001);
insert into card values(12348, '6642160005012195',sysdate()+interval 1160 day,1001);
insert into card values(12349, '6642160005012196',sysdate()+interval 5660 day,1002);
insert into card values(12350, '6642160005012197',sysdate()+interval 5640 day,1003);
insert into card values(12351, '6642160005012198',sysdate()+interval 5620 day,1003);

commit;


select * from card;
select * from customer;

CREATE TABLE Employee (
	employee_id INT PRIMARY KEY,
	employee_name VARCHAR(100) NOT NULL,
	email_id VARCHAR(255) UNIQUE NOT NULL,
	dob DATETIME NOT NULL,
	manufacturing_unit VARCHAR(10) NOT NULL
);

INSERT INTO Employee VALUES (2001, 'Jack', 'jack@mail.com', '1988-09-03', 'U001');
INSERT INTO Employee VALUES (2002, 'Jose', 'jose@mail.com', '1987-01-15', 'U002');
INSERT INTO Employee VALUES (2003, 'Jill', 'jill@mail.com', '1990-11-15','U001');
INSERT INTO Employee VALUES (2004, 'Albert', 'albert@mail.com', '1988-12-30','U003');
INSERT INTO Employee VALUES (2005, 'Husee', 'husee@mail.com', '1989-03-22','U001');

COMMIT;

SELECT * FROM Employee;

drop table if exists customer;
create table customer (
	customer_id BIGINT not null,
	city varchar(10),
	date_of_birth date,
	email_id varchar(20),
	name varchar(20),
	primary key (customer_id)
);
insert into customer (customer_id, city, date_of_birth, email_id,name) values (1001,'Vancouver','1992-01-10','monica@infy.com','Monica');
insert into customer (customer_id, city, date_of_birth, email_id,name) values (1002,'Seattle','1996-04-23','scott@infy.com','Scott');
commit;

select * from customer;

create table Account (
	account_number BIGINT not null,
	account_status varchar(10),
	account_type varchar(10),
	balance BIGINT,
	opening_date date,
	primary key (account_number)
);
insert into Account values (5001,'ACTIVE','Savings',12345,'2016-10-25');
insert into Account values (5002,'ACTIVE','Current',899567,'2015-01-21');
insert into Account values (5003,'INACTIVE','Loan',617345,'2016-11-12');
insert into Account values (5004,'ACTIVE','Savings',345324,'2017-12-02');
insert into Account values (5005,'ACTIVE','Demat',45324,'2017-04-08');
commit;
select * from Account;


CREATE TABLE Movie(
    movie_id int PRIMARY KEY,
    movie_name VARCHAR(50) UNIQUE NOT NULL,
    director_name VARCHAR(20) NOT NULL,
    imdb_rating float(4,2) NULL, 
    release_year int
);
  
INSERT INTO Movie VALUES(9001, 'Dunkirk', 'Christopher Nolan', 8.5, 2017);
INSERT INTO Movie VALUES(9002, 'The Emoji Movie', 'Tony Leondis', 4.5, 2017);
INSERT INTO Movie VALUES(9003, 'Annabelle', 'David Sandberg', 7.6, 2017);
INSERT INTO Movie VALUES(9004, 'The Amazing Spider-Man', 'Jon Watts', 7.9, 2012);
INSERT INTO Movie VALUES(9005, 'Wonder Woman', 'Patty Jenkins', 7.9, 2017);
INSERT INTO Movie VALUES(9006, 'Planet of the Apes', 'Matt Reeves', 8.0, 2011);
INSERT INTO Movie VALUES(9007, 'Atomic Blonde', 'David Leitch', 7.2, 2017);
INSERT INTO Movie VALUES(9008, 'Despicable Me 2', 'Kyle Balda', 6.4, 2013);
INSERT INTO Movie VALUES(9009, 'The Avengers', 'Joss Whedon', 8.1, 2012);
INSERT INTO Movie VALUES(9010, 'Avengers: Age of Ultron', 'Joss Whedon', 7.4, 2015);
INSERT INTO Movie VALUES(9011, 'Deadpool', 'Tim Miller', 8.0, 2016);
INSERT INTO Movie VALUES(9012, 'Downsizing', 'Alexander Payne', 5.9, 2017);
INSERT INTO Movie VALUES(9013, 'Avengers: Infinity War', 'Joss Whedon', NULL, 2018);
INSERT INTO Movie VALUES(9014, 'Deadpool2', 'Tim Miller', NULL, 2018);

commit;

SELECT * FROM Movie;
select * from Movie
where director_name = 'Joss Whedon' and imdb_rating in (select max(imdb_rating) from Movie where director_name = 'Joss Whedon');

select * from customer;

-- Spring Date
-- drop table customer;

create table customer(
   customer_id int,
   email_id varchar(50) unique,
   name varchar(20),
   date_of_birth date,
   
   constraint ps_customer_id_pk primary key (customer_id)
);
insert into customer (customer_id, email_id, name, date_of_birth) values (1, 'martin@infy.com', 'martin', '1994-05-20');
insert into customer (customer_id, email_id, name, date_of_birth) values (2, 'john@infy.com', 'john', '1993-05-20');
insert into customer (customer_id, email_id, name, date_of_birth) values (3, 'james@infy.com', 'james', '1997-05-20');
insert into customer (customer_id, email_id, name, date_of_birth) values (4, 'martin01@infy.com', 'martin', '2000-05-20');
insert into customer (customer_id, email_id, name, date_of_birth) values (5, null, 'tim', '2004-04-20');
commit;
select * from customer;


-- Spring Data Exercise -1 
drop table book;
CREATE TABLE BOOK(
	book_id INT,
    title VARCHAR(255),
    author_name VARCHAR(255),
    published_year DATE,
    publisher VARCHAR(255),
    isbn LONG,
    price INT
);
SET GLOBAL local_infile = true;
SHOW GLOBAL VARIABLES LIKE 'local_infile';
LOAD DATA LOCAL INFILE "H:\\LearningWorkspace\\Spring\\SpringUdemy\\mysqlscripts\\book.csv" INTO TABLE BOOK
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(book_id, title, author_name, published_year, publisher, isbn, price);

select * from book;
-- delete from book;

-- Pagination

create table transaction(
   transaction_id int auto_increment,
   transaction_date date,
   transaction_amount float,
   constraint ps_transaction_id_pk primary key (transaction_id)
);
insert into transaction values (1, sysdate()- interval 9136 day, 1000);
insert into transaction values (2, sysdate()- interval 8136 day, 1569);
insert into transaction values (3, sysdate()- interval 8736 day, 4567);
insert into transaction values (4, sysdate()- interval 9036 day, 2345);
insert into transaction values (5, sysdate()- interval 8636 day, 6745);
insert into transaction values (6, sysdate()- interval 8936 day, 2123);
insert into transaction values(7, sysdate()- interval 8836 day, 6789);
insert into transaction values (8, sysdate()- interval 9133 day, 3434);
insert into transaction values (9, sysdate()- interval 9026 day, 2000);
insert into transaction values (10, sysdate()- interval 8899 day, 6000);
insert into transaction values (11, sysdate()- interval 9166 day, 1500);
insert into transaction values (12, sysdate()- interval 9016 day, 1200);
insert into transaction values (13, sysdate()- interval 8916 day, 2500);
insert into transaction values (14, sysdate()- interval 8899 day, 6500);
insert into transaction values (15, sysdate()- interval 8899 day, 7000);
commit;

select * from transaction;


-- Pagination Exercises
create table players(
	player_id int auto_increment,
	player_name varchar(30),
	ranking int,
	batting_style varchar(10),
	bowling_style varchar(10),
	debut_date varchar(4),
	country varchar(20),
	constraint players_id_pk primary key (player_id)
);

insert into players values (1, 'Suzie Bates', 10, 'right-hand', 'right-arm', 2006, 'West Indies');
insert into players values (2, 'Amy Satterthwaite', 7, 'left-hand', 'right-arm', 2007, 'New Zealand');
insert into players values (3, 'Ellyse Perry', 3, 'right-hand', 'right-arm', 2007, 'Australia');
insert into players values (4, 'Stafanie Taylor', 1, 'right-hand', 'right-arm', 2008, 'West Indies');
insert into players values (5, 'Tammy Beaumont', 6, 'right-hand', 'NA', 2009, 'England');
insert into players values (6, 'Alyssa Healy', 2, 'right-hand', 'NA', 2010, 'Australia');
insert into players values (7, 'Meg Lanning', 5, 'right-hand', 'right-arm', 2010, 'Australia');
insert into players values (8, 'Lizelle Lee', 8, 'right-hand', 'right-arm', 2013, 'South Africa');
insert into players values (9, 'Smriti Mandhana', 4, 'left-hand', 'right-arm', 2014, 'India');
insert into players values (10, 'Laura Wolvaardt', 9, 'right-hand', 'NA', 2016, 'South Africa');

commit;

select * from players;

-- Primary Key Generation
create table customer (
	customer_id int auto_increment ,
	email_id varchar(20),
	name varchar(10),
	date_of_birth date,
	constraint ps_customer_id_pk primary key ( customer_id )
);
insert into customer (customer_id, email_id, name, date_of_birth) values (552092, 'martin@infy.com', 'Martin', '1994-01-10');
commit;
select * from customer;