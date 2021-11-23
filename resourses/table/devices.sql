create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('phone y4m', 10700.99), ('phone o', 10000.00), ('phone 123e', 3999.99),
('laptop abc3', 24800.50), ('laptop def7', 20000.00), ('headphones q4', 900.00), ('headphones a1', 9400.99);
insert into people(name) values('Ivan'), ('Anna'), ('Dmitiy'), ('Maria'), ('Kirill'), ('Olga');
insert into devices_people(device_id, people_id)  values(3, 1), (1, 2), (1, 5), (7, 2), (4, 3), (6, 5), (2, 3);

select avg(price) from devices;
select p.name, avg(d.price) from devices d inner join devices_people dp on d.id=dp.device_id
inner join people p on p.id=dp.people_id
group by p.name;

select p.name, avg(d.price) from devices d inner join devices_people dp on d.id=dp.device_id
inner join people p on p.id=dp.people_id
group by p.name
having avg(d.price) > 5000;