package ru.job4j.chat;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> logList = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (Scanner input = new Scanner(System.in)) {
            String in = input.nextLine();
            if (in.isBlank()) {
                throw new IllegalArgumentException("Ввведите слово/фразу/цифру");
            }
            boolean status = false;
            logList.add(in);
            List<String> phrases = readPhrases();
            int i = readPhrases().size() - 1;
            while (!OUT.equals(in.toLowerCase())) {
                if (STOP.equals(in.toLowerCase())) {
                    status = true;
                }
                if (CONTINUE.equals(in.toLowerCase())) {
                    status = false;
                }
                if (!status) {
                    String randomPhrase = phrases.get((int) (Math.random() * i));
                    System.out.println(randomPhrase);
                    logList.add(randomPhrase);
                }
                in = input.nextLine();
                logList.add(in);
            }
            saveLog(logList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] s = line.split(";");
                list.addAll(Arrays.asList(s));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(".\\src\\main\\java\\ru\\job4j\\chat\\log.txt",
                ".\\src\\main\\java\\ru\\job4j\\chat\\phrases.txt");
        cc.run();
    }
}

