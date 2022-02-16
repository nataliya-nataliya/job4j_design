package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

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

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        File o = new File(argsName.get("o"));
        if (args.length != 3) {
            throw  new IllegalArgumentException("Введите ключи и их значения в виде -key=value"
            + System.lineSeparator()
            + "-d - папка, которую нужно архивировать"
            + System.lineSeparator()
            + "-e - файлы, которые надо исключить (расширение с точкой)"
            + System.lineSeparator()
            + "-o - название архива с расширением .zip");
        }
        Path d = Paths.get(argsName.get("d"));
        if (!d.toFile().isDirectory()) {
            throw new IllegalArgumentException("Не существует папки, которую нужно архивировать");
        }
        Zip zip = new Zip();
        List<Path> list = Search.search(d, p -> !p.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(list, o);
    }
}
