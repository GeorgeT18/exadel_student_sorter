package com.exadel.studentsorter.util;

import com.exadel.studentsorter.exception.SorterNotFoundException;
import com.exadel.studentsorter.sorter.*;

import java.util.Comparator;

public abstract class SortUtil {
    public static <T> CustomSorter<T> getSorterByName(String name, Comparator<T> comparator) throws SorterNotFoundException {
        return switch (name) {
            case "bubble" -> new BubbleSorter<>(comparator);
            case "heap" -> new HeapSorter<>(comparator);
            case "merge" -> new MergeSorter<>(comparator);
            default -> throw new SorterNotFoundException("sorter with the given name " + name + " could not be found.");
        };
    }
}
