create table type(
	id serial primary key,
	name varchar(255)
);
create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) values ('СЛАДОСТИ'), ('МОЛОКО'), ('КРУПЫ'), ('СЫР');

insert into product(name, type_id, expired_date, price) values ('Торт', 1, '28/12/2021', 350.00);
insert into product(name, type_id, expired_date, price) values ('Мороженое', 1, '10/01/2022', 59.90);
insert into product(name, type_id, expired_date, price) values ('Печенье', 1, '10/06/2022', 70.00);
insert into product(name, type_id, expired_date, price) values ('Шоколад', 1, '14/12/2022', 91.00);
insert into product(name, type_id, expired_date, price) values ('Пирожное', 1, '05/08/2021', 125.50);
insert into product(name, type_id, expired_date, price) values ('Молоко топленое', 2, '28/12/2021', 65.00);
insert into product(name, type_id, expired_date, price) values ('Молоко овсяное', 2, '01/12/2021', 74.00);
insert into product(name, type_id, expired_date, price) values ('Рис', 3, '16/03/2022', 84.99);
insert into product(name, type_id, expired_date, price) values ('Перловая крупа', 3, '07/07/2022', 35.00);
insert into product(name, type_id, expired_date, price) values ('Гречневая крупа',3, '01/12/2021', 89.00);
insert into product(name, type_id, expired_date, price) values ('Сыр пармезан', 4, '24/11/2022', 280.00);
insert into product(name, type_id, expired_date, price) values ('Сыр плавленый', 4, '15/02/2022', 110.00);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 4, '20/11/2021', 109.00);

select p.name from product p join type on type.id = p.type_id 
where type.name = 'СЫР';

select p.name from product p join type on type.id = p.type_id
where lower(p.name) like '%мороженое%';

select p.name from product p join type on type.id = p.type_id
where expired_date < current_date;

select name from product 
where price = (select max(price) from product);

select type.name, count(*) from product p join type on type.id = p.type_id
group by type.name;

select p.name from product p join type on type.id = p.type_id
where type.name in('СЫР', 'МОЛОКО'); 

select type.name from product p join type on type.id = p.type_id
group by type.name
having count(*) < 10;

select p.name, type.name from product p join type on type.id = p.type_id;

select * from product;

