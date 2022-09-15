create table clients (
    client_id serial primary key,
    name_client varchar(50)
);

insert into clients (name_client) values ('Ivan Ivanov');
insert into clients (name_client) values ('Petr Petrov');
insert into clients (name_client) values ('Andrew Andreev');


create table authors (
    author_id serial primary key,
    name_author varchar(50)
);

insert into authors (name_author) values ('Bulgakov M.');
insert into authors (name_author) values ('Dostoevsky F.');

create table genre (
    genre_id serial primary key,
    name_genre varchar(200)
);

insert into genre (name_genre) values ('Novel');
insert into genre (name_genre) values ('Novella');

create table books (
    book_id serial primary key,
    name_book varchar(200),
    author_id integer references authors(author_id),
	genre_id integer references genre(genre_id)
);

insert into books (name_book, author_id, genre_id) values ('The Master and Margarita', 1, 1);
insert into books (name_book, author_id, genre_id) values ('A Young Doctor''s Notebook', 1, 2);
insert into books (name_book, author_id, genre_id) values ('Crime and Punishment', 2, 1);
insert into books (name_book, author_id, genre_id) values ('The Brothers Karamazov', 2, 1);
insert into books (name_book, author_id, genre_id) values ('The Gambler', 2, 2);


create table orders (
    order_id serial primary key,
    book_id integer references books(book_id),
    client_id integer references clients(client_id)
);

insert into orders (book_id, client_id) values (2, 1);
insert into orders (book_id, client_id) values (3, 1);
insert into orders (book_id, client_id) values (4, 2);
insert into orders (book_id, client_id) values (1, 3);
insert into orders (book_id, client_id) values (1, 2);
insert into orders (book_id, client_id) values (2, 3);
insert into orders (book_id, client_id) values (2, 2);

create view show_clients_with_1_or_more_book_novel
as select name_client, count(name_book) from clients
    join orders using(client_id)
    join books using(book_id)
    join authors using(author_id)
	join genre using(genre_id)
	where name_genre='Novel'
    group by (name_client) having count(name_book) >= 1 ;

select * from show_clients_with_1_or_more_book_novel;

alter view show_clients_with_1_or_more_book_novel rename to new_name;

select * from new_name;