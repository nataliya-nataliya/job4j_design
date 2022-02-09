package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        if (args.length != 2
        || !file.exists() || !file.isDirectory() || !file.isAbsolute()
        || !(".").equals(args[1].substring(0, 1))) {
            throw new IllegalArgumentException("Введите через пробел абсолютный путь папки поиска "
                    + "и расширение файла с точкой");
        }
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
