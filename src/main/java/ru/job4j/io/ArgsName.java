package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String s : args) {
            if (!s.startsWith("-")) {
                throw new IllegalArgumentException("Введите аргументы в виде -key=value");
            }
            int i = s.indexOf("=");
            if (s.length() - 1 == i) {
                throw new IllegalArgumentException("Укажите значение после \"=\"");
            }
            String key = s.substring(1, i);
            String value = s.substring(i + 1, s.length());
            values.put(key, value);
        }
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Введите аргументы");
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
