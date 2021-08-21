package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int row = 1; row < 10; row++) {
                for (int cell = 1; cell < 10; cell++) {
                    int multiple = cell * row;
                    if (multiple < 10) {
                        out.write((multiple + "  ").getBytes());
                    } else {
                        out.write((multiple + " ").getBytes());
                    }
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
