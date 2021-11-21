create table genres (
id serial primary key,
	genre VARCHAR(50)
);

insert into genres (genre) values ('Drama');
insert into genres (genre) values ('Horror');
insert into genres (genre) values ('Thriller');

create table movies (
id serial primary key,
movie varchar(50),
time_minutes int,
genre_id int references genres(id)
);

insert into movies (movie, genre_id) values ('Psycho', 3);
insert into movies (movie, time_minutes) values ('The Grand Budapest Hotel', 100);
insert into movies (movie, genre_id) values ('Alien', 2);
insert into movies (movie, genre_id, time_minutes) values ('The game', 3, 129);

select m.movie, g.genre from movies m join genres g on g.id = m.genre_id;
select m.movie Фильм, g.genre Жанр from movies m inner join genres g on g.id = m.genre_id;
select m.movie, m.time_minutes from movies m join genres g on g.id = m.genre_id;
