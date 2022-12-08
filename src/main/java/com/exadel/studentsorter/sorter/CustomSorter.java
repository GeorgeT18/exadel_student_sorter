package com.exadel.studentsorter.sorter;

import com.exadel.studentsorter.model.SortResult;

import java.util.Comparator;
import java.util.List;

public abstract class CustomSorter<T> implements Sorter<T> {
    protected abstract void sortImpl(List<T> list);

    protected Comparator<T> comparator;

    CustomSorter(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public SortResult sort(List<T> list) {
        long currentTimeInMilliseconds = System.currentTimeMillis();

        this.sortImpl(list);

        return new SortResult(System.currentTimeMillis() - currentTimeInMilliseconds);
    }
}
