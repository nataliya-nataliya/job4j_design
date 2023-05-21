package ru.job4j.odd.isp.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;


class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = () -> System.out.println("d");

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Go to the shop", STUB_ACTION);
        menu.add(Menu.ROOT, "Feed the dog", STUB_ACTION);
        menu.add("Go to the shop", "Buy groceries", STUB_ACTION);
        menu.add("Buy groceries", "Buy bread", STUB_ACTION);
        menu.add("Buy groceries", "Buy milk", STUB_ACTION);
        Assertions.assertEquals(new Menu.MenuItemInfo("Go to the shop",
                        List.of("Buy groceries"), STUB_ACTION, "1."),
                menu.select("Go to the shop").get());
        Assertions.assertEquals(new Menu.MenuItemInfo(
                        "Buy groceries",
                        List.of("Buy bread", "Buy milk"), STUB_ACTION, "1.1."),
                menu.select("Buy groceries").get());
        Assertions.assertEquals(new Menu.MenuItemInfo(
                        "Feed the dog", List.of(), STUB_ACTION, "2."),
                menu.select("Feed the dog").get());
    }

    @Test
    public void whenAddAndSelectThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Go to the shop", STUB_ACTION);
        Assertions.assertTrue(menu.select("Go to the shop").isPresent());
    }

    @Test
    public void whenPrintThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Go to the shop", STUB_ACTION);
        menu.add(Menu.ROOT, "Feed the dog", STUB_ACTION);
        menu.add("Go to the shop", "Buy groceries", STUB_ACTION);
        menu.add("Buy groceries", "Buy bread", STUB_ACTION);
        menu.add("Buy groceries", "Buy milk", STUB_ACTION);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        new ConsolePrinter().print(menu);
        String actual = outputStream.toString();
        String expected = "1.Go to the shop" + System.lineSeparator()
                + "----1.1.Buy groceries" + System.lineSeparator()
                + "--------1.1.1.Buy bread" + System.lineSeparator()
                + "--------1.1.2.Buy milk" + System.lineSeparator()
                + "2.Feed the dog" + System.lineSeparator()
                + System.lineSeparator();
        Assertions.assertEquals(expected, actual);
    }
}
