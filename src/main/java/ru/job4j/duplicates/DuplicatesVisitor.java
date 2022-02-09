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

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        if (map.containsKey(fileProperty)) {
            printAbsolutePath(fileProperty, file);
            map.get(fileProperty).add(file.toAbsolutePath());
        } else {
            map.put(fileProperty, new ArrayList<>());
            map.get(fileProperty).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

    public void printAbsolutePath(FileProperty fp, Path path) {
        if (map.get(fp).size() == 1) {
            System.out.println(map.get(fp).get(0).toAbsolutePath());
            System.out.println(path.toAbsolutePath());
        } else {
            System.out.println(path.toAbsolutePath());
        }
    }
}
