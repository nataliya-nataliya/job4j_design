package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;

public class Menu {
    public final int addPost = 1;
    public final int addManyPost = 2;
    public final int showAllPosts = 3;
    public final int deletePost = 4;

    public final String select = "Select menu";
    public final String count = "Select the number of posts to create";
    public final String textOfPost = "Enter text";
    public final String exit = "Exit";

    public final String menu = """
                Enter 1 to create a post.
                Enter 2 to create a certain number of posts.
                Enter 3 to show all posts.
                Enter 4 to delete all posts.
                Enter any other number to exit.
            """;

    public static void main(String[] args) {
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        Menu menu = new Menu();
        menu.start(commentGenerator, scanner, userGenerator, postStore);
    }

    private void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println(menu);
            System.out.println(select);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (addPost == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                userGenerator.generate();
                commentGenerator.generate();
                postStore.add(new Post(text, CommentGenerator.getComments()));
            } else if (addManyPost == userChoice) {
                System.out.println(textOfPost);
                String text = scanner.nextLine();
                System.out.println(count);
                int count = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < count; i++) {
                    createPost(commentGenerator, userGenerator, postStore, text);
                }
            } else if (showAllPosts == userChoice) {
                System.out.println(PostStore.getPosts());
            } else if (deletePost == userChoice) {
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(exit);
            }
        }
    }

    private static void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator, PostStore postStore, String text) {
        userGenerator.generate();
        commentGenerator.generate();
        postStore.add(new Post(text, CommentGenerator.getComments()));
    }
}
