create table company
(
    id integer not null,
    name character varying,
    constraint company_pkey primary key (id)
);

insert into company (id, name) values (1, 'Shoes');
insert into company (id, name) values (2, 'Toys');
insert into company (id, name) values (3, 'Health');
insert into company (id, name) values (4, 'Electronics');
insert into company (id, name) values (5, 'Jewelry');
insert into company (id, name) values (6, 'Computers');
insert into company (id, name) values (7, 'Grocery');
insert into company (id, name) values (8, 'Tools');

create table person
(
    id integer not null,
    name character varying,
    company_id integer references company(id),
    constraint person_pkey primary key (id)
);

insert into person (id, name, company_id) values (1, 'Hill', 5);
insert into person (id, name, company_id) values (2, 'Anna', 2);
insert into person (id, name, company_id) values (3, 'Homerus', 2);
insert into person (id, name, company_id) values (4, 'Denney', 7);
insert into person (id, name, company_id) values (5, 'Cherrita', 3);
insert into person (id, name, company_id) values (6, 'Lacy', 1);
insert into person (id, name, company_id) values (7, 'Collete', 8);
insert into person (id, name, company_id) values (8, 'Katy', 5);
insert into person (id, name, company_id) values (9, 'Tandy', 2);
insert into person (id, name, company_id) values (10, 'Minda', 7);
insert into person (id, name, company_id) values (11, 'Roana', 1);
insert into person (id, name, company_id) values (12, 'Olympia', 5);

select person.name, company.name from person join company
on person.company_id=company.id where company.id != 5;

select company.name, count(person.name) from person join company
on person.company_id=company.id
group by company.name
having count(person.name) =
(select  count(company_id) from person group by company_id
 order by count(company_id) desc limit 1);

