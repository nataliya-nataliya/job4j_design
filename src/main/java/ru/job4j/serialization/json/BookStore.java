package ru.job4j.serialization.json;

import java.util.Arrays;

public class BookStore {
    private final boolean stock;
    private final int idBook;
    private final String publishingHouse;
    private final Book book;
    private final String[] genre;

    public BookStore(boolean stock, int idBook, String publishingHouse, Book book, String[] genre) {
        this.stock = stock;
        this.idBook = idBook;
        this.publishingHouse = publishingHouse;
        this.book = book;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "BookStore{"
                + "stock=" + stock
                + ", idBook=" + idBook
                + ", publishingHouse='" + publishingHouse + '\''
                + ", book=" + book
                + ", genre=" + Arrays.toString(genre)
                + '}';
    }
}
