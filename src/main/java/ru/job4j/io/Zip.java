package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static void validation(String[] array, Path path) {
        if (array.length != 3) {
            throw  new IllegalArgumentException("Введите ключи и их значения в виде -key=value"
                    + System.lineSeparator()
                    + "-d - папка, которую нужно архивировать"
                    + System.lineSeparator()
                    + "-e - файлы, которые надо исключить (расширение с точкой)"
                    + System.lineSeparator()
                    + "-o - название архива с расширением .zip");
        } else if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("Не существует папки, которую нужно архивировать");
        }
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                 }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        File o = new File(argsName.get("o"));
        Path d = Paths.get(argsName.get("d"));
        validation(args, d);
        Zip zip = new Zip();
        List<Path> list = Search.search(d, p -> !p.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(list, o);
    }
}
