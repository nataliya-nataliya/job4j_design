package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw  new IllegalArgumentException("Не содержит ключ");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String s : args) {
            int i = s.indexOf("=");
            validation(s, i);
            String key = s.substring(1, i);
            String value = s.substring(i + 1, s.length());
            if (key != null && value != null) {
                values.put(key, value);
            }
        }
    }

    public static void validation(String string, int index) {
        if (!string.startsWith("-") || string.length() - 1 == index) {
            throw new IllegalArgumentException("Введите аргументы в виде -key=value");
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
