package ru.job4j.collection.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    SimpleMap<String, String> map;

    @Before
    public  void initData() {
         map = new SimpleMap<>();
    }

    @Test
    public void whenPut() {
        boolean mapPut = map.put("123", "abc");
        assertTrue(mapPut);
    }

    @Test
    public void whenPutEqualKey() {
        map.put("123", "abc");
        assertFalse(map.put("123", "abc"));
    }
    @Test
    public void whenPutNotEqualKey() {
        map.put("123", "abc");
        assertTrue(map.put("456", "abc"));
    }

    @Test
    public void whenGetValue() {
        map.put("123", "abc");
        assertThat(map.get("123"), is("abc"));
    }

    @Test
    public void whenGetValueAndAddedKeysAreEqual() {
        map.put("123", "abc");
        map.put("123", "efg");
        assertThat(map.get("123"), is("abc"));
    }

    @Test
    public void whenRemoveContainsKey() {
        map.put("123", "abc");
        assertTrue(map.remove("123"));
    }

    @Test
    public void whenRemoveNotContainsKey() {
        map.put("123", "abc");
        assertFalse(map.remove("456"));
    }
}