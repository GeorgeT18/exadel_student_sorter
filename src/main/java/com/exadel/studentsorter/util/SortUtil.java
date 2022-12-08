package com.exadel.studentsorter.util;

import com.exadel.studentsorter.exception.SorterNotFoundException;
import com.exadel.studentsorter.sorter.*;

public abstract class SortUtil {
    public static <T> CustomSorter<T> getSorterByName(String name) throws SorterNotFoundException {
        return switch (name) {
            case "bubble" -> new BubbleSorter<>();
            case "heap" -> new HeapSorter<>();
            case "merge" -> new MergeSorter<>();
            default -> throw new SorterNotFoundException("sorter with the given name " + name + " could not be found.");
        };
    }
}
