package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private static Properties properties;

    public TableEditor(Properties properties) {
        TableEditor.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        String driver = properties.getProperty("hibernate.connection.driver_class");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        return DriverManager.getConnection(url, login, password);
    }

    private void executeQuery(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "CREATE TABLE if not exists %s ();",
                tableName
        );
        executeQuery(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE if exists %s;",
                tableName
        );
        executeQuery(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE if exists %s ADD  if not exists %s %s;",
                tableName, columnName, type
        );
        executeQuery(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE if exists %s DROP %s;",
                tableName, columnName
        );
        executeQuery(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("ALTER TABLE if exists %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName
        );
        executeQuery(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s ", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            TableEditor tableEditor = new TableEditor(config);
            System.out.println("Создание таблицы");
            tableEditor.createTable("book_store");
            System.out.println(getTableScheme(tableEditor.connection, "book_store"));
            System.out.println("Добавление столбцов");
            tableEditor.addColumn("book_store", "author", "text");
            tableEditor.addColumn("book_store", "title", "text");
            tableEditor.addColumn("book_store", "amount", "int");
            System.out.println(getTableScheme(getConnection(), "book_store"));
            System.out.println("Переименование столбца");
            tableEditor.renameColumn("book_store", "author", "authors");
            System.out.println(getTableScheme(getConnection(), "book_store7"));
            System.out.println("Удаление столбца");
            tableEditor.dropColumn("book_store", "amount");
            System.out.println(getTableScheme(getConnection(), "book_store"));
            System.out.println("Удаление таблицы");
            tableEditor.dropTable("book_store");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
