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
    public static final String PATH_KEY_ARG = "path";
    public static final String OUT_KEY_ARG = "out";
    public static final String FILTER_KEY_ARG = "filter";
    public static final String STDOUT_VALUE_ARG = "stdout";
    public static final String DELIMITER_KEY_ARG = "delimiter";
    public static final String FILE_EXTENSION = ".csv";

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
        String namesOfColumns = list.get(0);
        if (!namesOfColumns.contains(delimiter)) {
            throw new IllegalArgumentException(
                    String.format("The delimiter \"%s\" does not match the delimiter in the file", delimiter));
        }
        String[] filterColumns = filter.split(delimiterColumnsArg);
        List<String> columns = Arrays.asList(namesOfColumns.split(delimiter));
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
        String path = argsName.get(PATH_KEY_ARG);
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("There is no path:%s", file));
        } else if (!path.endsWith(FILE_EXTENSION)) {
            throw new IllegalArgumentException(
                    String.format("The file %s does not have the file extension %s",
                            file.getName(), FILE_EXTENSION));
        }
        String delimiter = argsName.get(DELIMITER_KEY_ARG);
        String out = argsName.get(OUT_KEY_ARG);
        String filter = argsName.get(FILTER_KEY_ARG);
        List<String> listRows = csvReader.getRowsFromFile(path, delimiter);
        List<Integer> listIndexesColumns = csvReader.getIndexesColumns(listRows, filter, delimiter);
        String columns = csvReader.getColumns(listRows, listIndexesColumns, delimiter);
        if (out.equals(STDOUT_VALUE_ARG)) {
            csvReader.printList(columns);
        } else if (out.endsWith(FILE_EXTENSION)) {
            csvReader.writeCSV(columns, out);
        } else {
            throw new IllegalArgumentException(String.format(
                    "The value of the path parameter must be \"%s\" or a file with the file extension %s",
                    STDOUT_VALUE_ARG, FILE_EXTENSION));
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
