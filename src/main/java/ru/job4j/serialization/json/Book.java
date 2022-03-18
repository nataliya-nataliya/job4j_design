package ru.job4j.serialization.json;

public class Book {
    private final String author;
    private final String nameBook;

    public Book(String author, String nameBook) {
        this.author = author;
        this.nameBook = nameBook;
    }

    @Override
    public String toString() {
        return "Book{"
                + "author='" + author + '\''
                + ", nameBook='" + nameBook + '\''
                + '}';
    }
}
