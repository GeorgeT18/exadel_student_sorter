package com.exadel.studentsorter.sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSorter<T> extends CustomSorter<T> {
    @Override
    protected void sortImpl(List<T> list, Comparator<T> sortByValueComparator) {
        this.mergeSort(list, list.size(), sortByValueComparator);
    }

    protected void mergeSort(List<T> list, int n, Comparator<T> sortByValueComparator) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        List<T> l = new ArrayList<>();
        List<T> r = new ArrayList<>();

        for (int i = 0; i < mid; i++) {
            l.add(i, list.get(i));
        }
        for (int i = mid; i < n; i++) {
            r.add(i - mid, list.get(i));
        }
        mergeSort(l, mid, sortByValueComparator);
        mergeSort(r, n - mid, sortByValueComparator);

        merge(list, l, r, mid, n - mid, sortByValueComparator);
    }

    protected void merge(List<T> list, List<T> l, List<T> r, int left, int right, Comparator<T> sortByValueComparator) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (sortByValueComparator.compare(l.get(i), r.get(j)) > 0) {
                list.set(k++, r.get(j++));
            }
            else {
                list.set(k++, l.get(i++));
            }
        }
        while (i < left) {
            list.set(k++, l.get(i++));
        }
        while (j < right) {
            list.set(k++, r.get(j++));
        }
    }
}
