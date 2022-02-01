package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalysisTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        File source = folder.newFile("unavailable.csv");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 01:00:01");
            out.println("300 01:00:31");
            out.println("500 01:01:00");
            out.println("400 01:01:05");
            out.println("200 01:01:50");
            out.println("500 01:02:03");
            out.println("300 01:03:10");
            out.println("200 01:03:30");
        }
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                rsl.append(line);
            }
        }
        assertThat(rsl.toString(), is("01:01:00;01:01:50"
                + "01:02:03;01:03:10"));
    }
}
