package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMinTest {
    @Test
    public void checkMaxIntElFromIntList() {
        List<Integer> list = List.of(3, 6, 2, 9);
        int expected = Collections.max(list);
        MaxMin maxMin = new MaxMin();
        BiPredicate<Integer, Integer> biPredicate = maxMin.max(Comparator.comparingInt(Integer::intValue));
        int actual = maxMin.getElFromListByCondition(list, biPredicate);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkMaxIntElFromIntListOnlyOneElement() {
        List<Integer> list = List.of(3);
        int expected = Collections.max(list);
        MaxMin maxMin = new MaxMin();
        BiPredicate<Integer, Integer> biPredicate = maxMin.max(Comparator.comparingInt(Integer::intValue));
        int actual = maxMin.getElFromListByCondition(list, biPredicate);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkMaxIntElFromIntListIsEmpty() {
        List<Integer> list = new ArrayList<>();
        Integer expected = null;
        MaxMin maxMin = new MaxMin();
        BiPredicate<Integer, Integer> biPredicate = maxMin.max(Comparator.comparingInt(Integer::intValue));
        Integer actual = maxMin.getElFromListByCondition(list, biPredicate);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkMinIntElFromIntList() {
        List<Integer> list = List.of(3, 6, 2, 9);
        int expected = Collections.min(list);
        MaxMin maxMin = new MaxMin();
        BiPredicate<Integer, Integer> biPredicate = maxMin.min(Comparator.comparingInt(Integer::intValue));
        int actual = maxMin.getElFromListByCondition(list, biPredicate);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkFirstAscStringElFromIntList() {
        List<String> list = List.of("window", "door", "roof", "floor");
        String expected = Collections.min(list);
        MaxMin maxMin = new MaxMin();
        BiPredicate<String, String> biPredicate = maxMin.min(Comparator.comparing(String::toString));
        String actual = maxMin.getElFromListByCondition(list, biPredicate);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkFirstDescStringElFromIntList() {
        List<String> list = List.of("window", "door", "roof", "floor");
        String expected = Collections.max(list);
        MaxMin maxMin = new MaxMin();
        BiPredicate<String, String> biPredicate = maxMin.max(Comparator.comparing(String::toString));
        String actual = maxMin.getElFromListByCondition(list, biPredicate);
        Assert.assertEquals(expected, actual);
    }
}
