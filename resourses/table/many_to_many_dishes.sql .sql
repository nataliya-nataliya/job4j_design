-- many-to-one

create table dishes(
id serial primary key,
name varchar(255)
);

create table possible_topping(
id serial primary key,
name varchar(255)
);

create table dishes_topping(
id serial primary key,
dishes_id int references dishes(id),
topping_id int references possible_topping(id)
);

insert into dishes(name) values('pizza'), ('pasta'), ('sushi');
insert into possible_topping(name) values('cheese'), ('tomato'), ('bell pepper'), ('salmon'), ('cashew'), ('cucumber');
insert into dishes_topping(dishes_id, topping_id) values
(1, 1), (1, 2), (1, 3), (1, 6), (2, 1), (2, 2), (2, 3), (2, 5), (3, 4), (3, 6);

select * from dishes_topping;
select * from dishes_topping where id in (select id from possible_topping); 