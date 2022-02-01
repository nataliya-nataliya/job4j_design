package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintStream out = new PrintStream(target)) {
            boolean on = true;
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                String[] s = line.split(" ");
                if (!on && (s[0].equals("200")  || s[0].equals("300"))) {
                    out.println(s[1]);
                    on = true;
                } else if (on && (s[0].equals("400") || s[0].equals("500"))) {
                        out.print(s[1] + ";");
                        on = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("./data/unavailable.csv", "./data/target.txt");
    }
}
