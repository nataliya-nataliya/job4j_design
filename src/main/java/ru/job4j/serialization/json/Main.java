package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final BookStore bookStore = new BookStore(false, 12345,
                "A",
                new Book("Kuprin A. I.", "Granatoviy braslet"),
                new String[] {"realism", "short story"});

        /* Преобразуем объект bookStore в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(bookStore));

        /* Модифицируем json-строку */
        final String bookStoreJson =
                "{"
                        + "\"stock\":false,"
                        + "\"idBook\":54321,"
                        + "\"publishingHouse\":B,"
                        + "\"book\":"
                        + "{"
                        + "\"author\":\"Bunin A. I.\","
                        + "\"nameBook\":\"Temnie allei\""
                        + "},"
                        + "\"genre\":"
                        + "[\"romanticizm\",\"short story\"]"
                        + "}";
        final BookStore bookStoreMod = gson.fromJson(bookStoreJson, BookStore.class);
        System.out.println(bookStoreMod);
    }
}
