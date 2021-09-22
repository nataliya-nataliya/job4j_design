create table employees(
	id serial primary key,
	name varchar(255),
	age int,
	remote_work boolean
);
insert into employees(name, age, remote_work) values('Anna', 20, true);
select * from employees;
update employees set age = 21;
select * from employees;
delete from employees;
select * from employees;