drop database if exists customer_db;
create database customer_db;
use customer_db;

create table services (
	service_id int not null,
	service_name varchar(20),
	service_cost int,
	primary key (service_id)
);
create table customer (
	customer_id int not null auto_increment,
	date_of_birth date,
	emailid varchar(20),
	name varchar(20),
	primary key (customer_id)
);
create table customer_service (
	cust_id int not null,
	serv_id int not null
);

alter table customer_service add constraint fk_services_mapping foreign key (serv_id) references services(service_id);
alter table customer_service add constraint fk_customer_mapping foreign key (cust_id) references customer(customer_id);


INSERT INTO customer (customer_id, emailid, name, date_of_birth) VALUES (1001,'steven@infy.com', 'Steven Martin', sysdate()-interval 7476 day);
INSERT INTO customer (customer_id, emailid, name, date_of_birth) VALUES (1002,'kevin@infy.com', 'Kevin Nelson', sysdate()-interval 11374 day);
INSERT INTO customer (customer_id, emailid, name, date_of_birth) VALUES (1003,'john@infy.com', 'John Matric', sysdate()-interval 12344 day);
INSERT INTO customer (customer_id, emailid, name, date_of_birth) VALUES (1004,'chan@infy.com', 'Chan mathew', sysdate()-interval 10344 day);
INSERT INTO customer (customer_id, emailid, name, date_of_birth) VALUES (1005,'jill@infy.com', 'Jill mathew', sysdate()-interval 11374 day);


INSERT INTO services (service_id, service_name, service_cost) values (3001,'Internet Banking',15);
INSERT INTO services (service_id, service_name, service_cost) values (3002,'Phone Banking',10);
INSERT INTO services (service_id, service_name, service_cost) values (3003,'Mobile Banking',20);



INSERT INTO customer_service (cust_id,serv_id) values (1001,3001);
INSERT INTO customer_service (cust_id,serv_id) values (1001,3002);
INSERT INTO customer_service (cust_id,serv_id) values (1002,3003);
INSERT INTO customer_service (cust_id,serv_id) values (1003,3001);
INSERT INTO customer_service (cust_id,serv_id) values (1003,3003);

commit;
select * from customer;
select * from services;
select * from customer_service;
