package com.exadel.studentsorter.sorter;

import com.exadel.studentsorter.model.SortResult;

import java.util.Comparator;
import java.util.List;

public abstract class CustomSorter<T> implements Sorter<T> {
    protected abstract void sortImpl(List<T> list, Comparator<T> sortByValueComparator);

    public SortResult sort(List<T> list, Comparator<T> sortByValueComparator) {
        long currentTimeInMilliseconds = System.currentTimeMillis();

        this.sortImpl(list, sortByValueComparator);

        return new SortResult(System.currentTimeMillis() - currentTimeInMilliseconds);
    }
}
