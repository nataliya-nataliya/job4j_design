package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "bookStore")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookStore {
    @XmlAttribute
    private boolean stock;

    @XmlAttribute
    private int idBook;

    @XmlAttribute
    private String publishingHouse;

    @XmlElement
    private Book book;

    @XmlElementWrapper
    @XmlElement(name = "genre")
    private String[] genres;

    public BookStore() {

    }

    public BookStore(boolean stock, int idBook, String publishingHouse, Book book, String[] genres) {
        this.stock = stock;
        this.idBook = idBook;
        this.publishingHouse = publishingHouse;
        this.book = book;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "BookStore{"
                + "stock=" + stock
                + ", idBook=" + idBook
                + ", publishingHouse='" + publishingHouse + '\''
                + ", book=" + book
                + ", genres=" + Arrays.toString(genres)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final BookStore bookStore = new BookStore(false, 12345,
                "A",
                new Book("Kuprin A. I.", "Granatoviy braslet"),
                new String[]{"realism", "short story"});

        JAXBContext context = JAXBContext.newInstance(BookStore.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(bookStore, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
        }
    }
}
