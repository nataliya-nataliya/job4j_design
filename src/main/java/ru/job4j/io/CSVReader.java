package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public final String delimiterColumnsArg = ",";
    public static final int NUMBER_OF_PARAMETERS = 4;

    public List<String> getRowsFromFile(String path, String delimiter) {
        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(Files.newInputStream(Paths.get(path)))
                .useDelimiter(delimiter)) {
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Integer> getIndexesColumns(List<String> list, String filter, String delimiter) {
        String[] filterColumns = filter.split(delimiterColumnsArg);
        List<String> columns = Arrays.asList(list.get(0).split(delimiter));
        List<Integer> indexes = new ArrayList<>();
        for (String filterColumn : filterColumns) {
            int index = columns.indexOf(filterColumn);
            if (index != -1) {
                indexes.add(index);
            }
        }
        return indexes;
    }

    public String getColumns(List<String> list, List<Integer> indexes, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String row : list) {
            List<String> columns = Arrays.asList(row.split(delimiter));
            for (int i = 0; i < indexes.size(); i++) {
                stringBuilder.append(columns.get(indexes.get(i)));
                if (i != indexes.size() - 1) {
                    stringBuilder.append(delimiter);
                }
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public void writeCSV(String rows, String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(rows);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void printList(String rows) {
        System.out.print(rows);
    }

    public static void handle(ArgsName argsName) {
        CSVReader csvReader = new CSVReader();
        String path = argsName.get("path");
        if (!new File(path).exists()) {
            throw new IllegalArgumentException(String.format("There is no path:%s", path));
        }
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        List<String> listRows = csvReader.getRowsFromFile(path, delimiter);
        List<Integer> listIndexesColumns = csvReader.getIndexesColumns(listRows, filter, delimiter);
        String columns = csvReader.getColumns(listRows, listIndexesColumns, delimiter);
        if (out.equals("stdout")) {
            csvReader.printList(columns);
        } else {
            csvReader.writeCSV(columns, out);
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != NUMBER_OF_PARAMETERS) {
            throw new IllegalArgumentException(
                    String.format("The number of parameters is not equal to %d", NUMBER_OF_PARAMETERS));
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
