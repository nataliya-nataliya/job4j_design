package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
public class Book {

    @XmlAttribute
    private String author;

    @XmlAttribute
    private String nameBook;

    public Book() {

    }

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
