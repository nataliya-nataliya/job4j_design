package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach((s) -> {
                count.getAndIncrement();
                String[] nameEmail = s.trim().split(";");
                if (nameEmail.length == 2
                        && !nameEmail[0].isBlank() && !nameEmail[1].isBlank()) {
                    users.add(new User(nameEmail[0], nameEmail[1]));
                }
                if (nameEmail.length == 0 || nameEmail.length == 1 || nameEmail.length == 2) {
                    throw  new IllegalArgumentException("В строке " + count + " нет имени, e-mail  или ни того, ни другого");
                }
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name, email) values (?,?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}