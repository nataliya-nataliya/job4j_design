create table departments(
id serial primary key,
name varchar
);
create table employees(
id serial primary key,
name varchar,
department_id int references departments(id)
);

insert into departments (name) values ('Business Development');
insert into departments (name) values ('Human Resources');
insert into departments (name) values ('Marketing');
insert into departments (name) values ('Sales');
insert into departments (name) values ('Accounting');

insert into employees (name, department_id) values ('Yance', 4);
insert into employees (name, department_id) values ('Cassondra', 2);
insert into employees (name, department_id) values ('Clea', 3);
insert into employees (name, department_id) values ('Vallie', 4);
insert into employees (name, department_id) values ('Berthe', 4);
insert into employees (name, department_id) values ('Zechariah', 2);
insert into employees (name, department_id) values ('Farrell', null);
insert into employees (name, department_id) values ('Salomone', 5);
insert into employees (name, department_id) values ('Corri', 3);
insert into employees (name, department_id) values ('Constantino', 4);

select * from departments d left join employees e on e.department_id = d.id;
select * from departments d right join employees e on e.department_id = d.id;
select * from departments d full join employees e on e.department_id = d.id;
select * from departments cross join employees;
select d.name from departments d left join employees e on e.department_id = d.id
where e.department_id is null;

select * from departments d left join employees e on e.department_id = d.id;
select * from employees e right join departments d on e.department_id = d.id;

select * from employees e left join departments d on e.department_id = d.id;
select * from departments d right join employees e on e.department_id = d.id;




create table teens1 (
	int serial primary key,
	name varchar(50),
	gender varchar(8)
);

create table teens2 (
	int serial primary key,
	name varchar(50),
	gender varchar(8)
);

insert into teens1 (name, gender) values ('Marcelle', 'Male');
insert into teens1 (name, gender) values ('Christie', 'Female');
insert into teens1 (name, gender) values ('John', 'Male');
insert into teens1 (name, gender) values ('Anna', 'Female');
insert into teens1 (name, gender) values ('Aurelea', 'Female');
insert into teens1 (name, gender) values ('Petr', 'Male');
insert into teens1 (name, gender) values ('Fred', 'Male');

insert into teens2 (name, gender) values ('Marcelle', 'Male');
insert into teens2 (name, gender) values ('Christie', 'Female');
insert into teens2 (name, gender) values ('John', 'Male');
insert into teens2 (name, gender) values ('Anna', 'Female');
insert into teens2 (name, gender) values ('Aurelea', 'Female');
insert into teens2 (name, gender) values ('Petr', 'Male');
insert into teens2 (name, gender) values ('Fred', 'Male');

select * from teens1 cross join teens2
where teens1.gender != teens2.gender;


