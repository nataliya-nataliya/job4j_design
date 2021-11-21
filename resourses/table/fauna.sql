create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('Common turkey', 1450, null);
insert into fauna (name, avg_age, discovery_date) values ('Swordfish', 4015, '01/01/1758');
insert into fauna (name, avg_age, discovery_date) values ('Catfish', 25550, null);
insert into fauna (name, avg_age, discovery_date) values ('Skywalker hoolock gibbon', 9125, '01/01/2017');
insert into fauna (name, avg_age, discovery_date) values ('Alligator', 18250, null);

select *from fauna
where name like '%fish%';

select *from fauna
where avg_age between 10000 and 21000;

select *from fauna
where discovery_date is null;

select *from fauna
where discovery_date < '01/01/1950';
