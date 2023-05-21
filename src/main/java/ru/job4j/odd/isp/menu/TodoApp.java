package ru.job4j.odd.isp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TodoApp {
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");
    private static final int ADD_ROOT_ELEMENT = 1;
    private static final int ADD_CHILD_ELEMENT = 2;
    private static final int ACTION = 3;
    private static final int OUT_MENU = 4;

    private static final String IN_ROOT_ELEMENT = "Enter root element";
    private static final String IN_CHILD_ELEMENT = "Enter child element";
    private static final String IN_SELECT_ELEMENT = "Enter element";


    public static final String MENU = """
                Enter 1 to add root element.
                Enter 2 to add child element.
                Enter 3 to do action.
                Enter 4 to print menu.
                Enter any other number to exit.
            """;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        Menu menu = new SimpleMenu();
        MenuPrinter consolePrinter = new ConsolePrinter();
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_ROOT_ELEMENT == userChoice) {
                System.out.println(IN_ROOT_ELEMENT);
                String rootElement = scanner.nextLine();
                menu.add(Menu.ROOT, rootElement, DEFAULT_ACTION);
            } else if (ADD_CHILD_ELEMENT == userChoice) {
                System.out.println(IN_ROOT_ELEMENT);
                String rootElement = scanner.nextLine();
                System.out.println(IN_CHILD_ELEMENT);
                String childElement = scanner.nextLine();
                menu.add(rootElement, childElement, DEFAULT_ACTION);
            } else if (ACTION == userChoice) {
                System.out.println(IN_SELECT_ELEMENT);
                String element = scanner.nextLine();
                Optional<Menu.MenuItemInfo> itemInfo = menu.select(element);
                itemInfo.ifPresentOrElse(el -> itemInfo.get().getActionDelegate().delegate(),
                        () -> System.out.printf("There isn't element \"%s\"%n", element)
                );
            } else if (OUT_MENU == userChoice) {
                consolePrinter.print(menu);
            } else {
                run = false;
                System.out.println("End");
            }
        }
    }

    public static void main(String[] args) {
        TodoApp todoApp = new TodoApp();
        todoApp.run();
    }
}
