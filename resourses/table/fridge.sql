create table fridge (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    shelf integer
);

insert into fridge (name, count, shelf) VALUES ('butter', 1, 1);
insert into fridge (name, count, shelf) VALUES ('milk', 2, 1);
insert into fridge (name, count, shelf) VALUES ('bread', 1, 2);

insert into fridge (name, count, shelf) VALUES ('egg', 10, 3);
delete from fridge where shelf = 2;
update fridge set shelf = 5 where name like 'm%';

update fridge set count = 5 where name like 'm%';
update fridge set count = 5 where name like 'b%';
