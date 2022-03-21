package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonBook = new JSONObject("{\"author\":\"Kuprin A. I.\", \"nameBook\":\"Granatoviy braslet\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("novel");
        JSONArray jsonGenres = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final BookStore bookStore = new BookStore(false, 12345,
                "A",
                new Book("Kuprin A. I.", "Granatoviy braslet"),
                new String[] {"realism", "short story"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stock", bookStore.isStock());
        jsonObject.put("idBook", bookStore.getIdBook());
        jsonObject.put("publishingHouse", bookStore.getPublishingHouse());
        jsonObject.put("book", jsonBook);
        jsonObject.put("genres", jsonGenres);

        /* Вывод результата в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразование объекта bookStore в json-строку */
        System.out.println(new JSONObject(bookStore).toString());
    }
}
