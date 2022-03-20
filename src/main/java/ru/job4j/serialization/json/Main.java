package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BookStore bookStore = new BookStore(false, 12345,
                "A",
                new Book("Kuprin A. I.", "Granatoviy braslet"),
                new String[] {"realism", "short story"});

        JAXBContext context = JAXBContext.newInstance(BookStore.class);
        /* Создание сериализатора */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализация */
            marshaller.marshal(bookStore, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализация */
            BookStore result = (BookStore) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
