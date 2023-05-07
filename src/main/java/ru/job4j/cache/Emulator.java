package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    public final int listOfFiles = 1;
    public final int loadingContentsIntoCache = 2;
    public final int gettingContentFromCache = 3;

    public final String select = "Select menu";
    public final String nameFile = "Enter name of file";
    public final String nameDirectory = "Enter directory";
    public final String exit = "Exit";

    public final String menu = """
                Enter 1 to list files.
                Enter 2 to load file contents into cache.
                Enter 3 to get file content from cache.
                Enter any other number to exit.
            """;

    private void start(Scanner scanner) {
        System.out.println(nameDirectory);
        String nameDirectory = scanner.nextLine();
        DirFileCache dirFileCache = new DirFileCache(nameDirectory);
        boolean run = true;
        while (run) {
            System.out.println(menu);
            System.out.println(select);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (listOfFiles == userChoice) {
                System.out.println(dirFileCache.getFiles(nameDirectory));
            } else if (loadingContentsIntoCache == userChoice) {
                System.out.println(nameFile);
                String text = scanner.nextLine();
                dirFileCache.put(text, dirFileCache.load(text));
            } else if (gettingContentFromCache == userChoice) {
                System.out.println(nameFile);
                String text = scanner.nextLine();
                System.out.println(dirFileCache.get(text));
            } else {
                run = false;
                System.out.println(exit);
            }
        }
    }

    public static void main(String[] args) {
/*        Name of directory with files:
        src/main/java/ru/job4j/cache/cacheFiles/
 */
        Scanner scanner = new Scanner(System.in);
        Emulator emulator = new Emulator();
        emulator.start(scanner);
    }
}
