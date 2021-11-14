-- many-to-one

create table working_mode(
id serial primary key,
	name varchar(15)
	);

create table employees(
    id serial primary key,
    name varchar(255),
    working_mode_id int references working_mode(id)
);

insert into working_mode(name) values ('remote'), ('office'), ('mix_mode');
insert into employees(name, working_mode_id) VALUES ('Anna', 2), ('Dmitriy', 1), ('Elena', 1), ('Pavel', 3), ('Inna', 1) ;

select * from employees;

select * from working_mode where id in (select id from employees);