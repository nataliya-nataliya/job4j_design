create table car_body(
id serial primary key,
name varchar);
insert into car_body (name) values ('Sedan');
insert into car_body (name) values ('Van');
insert into car_body (name) values ('Crossover');
insert into car_body (name) values ('limousine');


create table engine(
id serial primary key,
name varchar);
insert into engine (name) values ('V8');
insert into engine (name) values ('V10');
insert into engine (name) values ('V12');

create table transmission(
id serial primary key,
name varchar);
insert into transmission (name) values ('manual');
insert into transmission (name) values ('automatic');
insert into transmission (name) values ('robotic');

create table car(
id serial primary key,
name varchar,
car_body_id int references car_body(id),
engine_id int references engine(id),
transmission_id int references transmission(id));
insert into car (name, car_body_id, engine_id,transmission_id) 
values ('car1', 1, 1, 2),
('car2', 2, null, 2),
('car3', 3, 1, null),
('car4', 1, 2, 3);

select *from car;
select car.name, car_body.name, engine.name, transmission.name from car 
left join car_body on car.car_body_id=car_body.id
left join engine on car.engine_id=engine.id
left join transmission on car.transmission_id=transmission.id;

select car_body.name from car
right join car_body on car.car_body_id=car_body.id
where car.car_body_id is null;

select engine.name from car
right join engine on car.engine_id=engine.id
where car.engine_id is null;

select transmission.name from car
right join transmission on car.transmission_id=transmission.id
where car.transmission_id is null;