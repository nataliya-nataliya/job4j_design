-- one-to-one

create table library_card(
    id serial primary key,
    number int
);

create table person(
    id serial primary key,
    name varchar(255),
    card_id int references library_card(id) unique
	);

insert into library_card(number) values(12345), (67890);

insert into person(name, card_id) values('Ivan', 2), ('Ekaterina', 1);

select *from library_card;
select *from person;