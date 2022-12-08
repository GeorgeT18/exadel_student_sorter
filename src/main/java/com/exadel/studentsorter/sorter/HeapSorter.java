package com.exadel.studentsorter.sorter;

import java.util.Comparator;
import java.util.List;

public class HeapSorter<T> extends CustomSorter<T> {
    public HeapSorter(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    protected void sortImpl(List<T> list) {
        int size = list.size();

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(list, size, i);
        }

        for (int i = size - 1; i > 0; i--) {
            T temp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, temp);

            heapify(list, i, 0);
        }
    }

    protected void heapify(List<T> list, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && this.comparator.compare(list.get(l), list.get(largest)) > 0) {
            largest = l;
        }

        if (r < n && this.comparator.compare(list.get(r), list.get(largest)) > 0) {
            largest = r;
        }

        if (largest != i) {
            T temp = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, temp);

            heapify(list, n, largest);
        }
    }
}
