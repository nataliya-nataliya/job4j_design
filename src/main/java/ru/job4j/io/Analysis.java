package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintStream out = new PrintStream(target)) {
            boolean on = true;
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                String[] s = line.split(" ");
                if (!on && ("200".equals(s[0])  || "300".equals(s[0]))) {
                    out.println(s[1]);
                    on = true;
                } else if (on && ("400".equals(s[0]) || "500".equals(s[0]))) {
                        out.print(s[1] + ";");
                        on = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysisAccess = new Analysis();
        analysisAccess.unavailable("./data/unavailable.csv", "./data/target.txt");
    }
}
