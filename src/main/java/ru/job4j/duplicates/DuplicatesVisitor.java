package ru.job4j.duplicates;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> map = new HashMap<>();

    /**
     * Проверка на размер ArrayList добавлена, чтобы вывести абсолютный путь первого файла,
     * у которого найден дубликат
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        if (map.containsKey(fileProperty)) {
            if (map.get(fileProperty).size() == 1) {
                System.out.println(map.get(fileProperty).get(0).toAbsolutePath());
            }
            map.get(fileProperty).add(file.toAbsolutePath());
            System.out.println(file.toAbsolutePath());
        } else {
            map.put(fileProperty, new ArrayList<>());
            map.get(fileProperty).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
