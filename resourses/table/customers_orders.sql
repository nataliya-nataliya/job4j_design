create table customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers (first_name, last_name, age, country) values ('Adelaida', 'Atkirk', 31, 'China');
insert into customers (first_name, last_name, age, country) values ('Alex', 'Charlet', 23, 'Indonesia');
insert into customers (first_name, last_name, age, country) values ('Prudi', 'Ricciardello', 40, 'Libya');
insert into customers (first_name, last_name, age, country) values ('Reynard', 'Ewan', 56, 'Belgium');
insert into customers (first_name, last_name, age, country) values ('Ibbie', 'Luck', 23, 'Brazil');

select * from customers where age = (select min(age) from customers);

create table orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);
insert into orders (amount, customer_id) values (4, 1);
insert into orders (amount, customer_id) values (5, 3);
insert into orders (amount, customer_id) values (1, 5);

select * from customers where customers.id not in (select customer_id from orders);