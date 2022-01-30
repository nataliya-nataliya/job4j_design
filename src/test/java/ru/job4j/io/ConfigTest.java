package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment_and_empty_srting.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is(config.value("name")));
        assertThat(config.value("surname"), is(config.value("surname")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutKeyAndValue() {
        String path = "./data/pair_without_key_and_value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithoutValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is(Matchers.nullValue()));
    }
}

