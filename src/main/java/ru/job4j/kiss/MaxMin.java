package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    public <T> T getElFromListByCondition(List<T> list, BiPredicate<T, T> biPredicate) {
        T element;
        if (list.size() != 0) {
            element = list.get(0);
            for (T t : list) {
                if (biPredicate.test(t, element)) {
                    element = t;
                }
            }
        } else {
            element = null;
        }
        return element;
    }

    public <T> BiPredicate<T, T> min(Comparator<T> comparator) {
        return (t, u) -> comparator.compare(t, u) < 0;
    }

    public <T> BiPredicate<T, T> max(Comparator<T> comparator) {
        return (t, u) -> comparator.compare(t, u) > 0;
    }
}
