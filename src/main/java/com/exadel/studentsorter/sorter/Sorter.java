package com.exadel.studentsorter.sorter;

import com.exadel.studentsorter.model.SortResult;

import java.util.List;

public interface Sorter<T> {
    public SortResult sort(List<T> list);
}
