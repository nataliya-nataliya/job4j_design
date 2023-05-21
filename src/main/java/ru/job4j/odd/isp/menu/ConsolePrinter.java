package ru.job4j.odd.isp.menu;

public class ConsolePrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        String indentation = "----";
        String delimiter = "\\.";
        StringBuilder stringBuilder = new StringBuilder();
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int numberOfIndentations = menuItemInfo.getNumber().split(delimiter).length - 1;
            stringBuilder.append(indentation.repeat(numberOfIndentations));
            stringBuilder.append(menuItemInfo.getNumber());
            stringBuilder.append(menuItemInfo.getName());
            stringBuilder.append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }
}
