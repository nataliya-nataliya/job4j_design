CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    name text,
    director text
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title text,
    author text
);
INSERT INTO movie (name, director)
VALUES ('The Martian', 'Ridley Scott'),
       ('The Matrix', 'The Wachowskis'),
       ('The Lord of the  Rings', 'Peter Jackson'),
       ('Harry Potter and the Prisoner of Azkaban', 'Alfonso Cuarón'),
       ('Iron Man', 'Jon Favreau');

INSERT INTO book (title, author)
VALUES ('Harry Potter and the Prisoner of Azkaban', 'J. K. Rowling'),
       ('The Lord of the  Rings', 'J. R. R. Tolkien'),
       ('1984', 'George Orwell'),
       ('The Martian', 'Andy Weir'),
       ('Divine Comedy', 'Dante Alighieri');

SELECT name
FROM movie
INTERSECT
SELECT title
FROM book;

SELECT title
FROM book
EXCEPT
SELECT name
FROM movie;

(SELECT title
FROM book
EXCEPT
SELECT name
FROM movie)
UNION ALL
(SELECT name
FROM movie
EXCEPT
SELECT title
FROM book);