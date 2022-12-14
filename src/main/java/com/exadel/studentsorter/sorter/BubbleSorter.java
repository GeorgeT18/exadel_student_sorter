package com.exadel.studentsorter.sorter;

import java.util.Comparator;
import java.util.List;

public class BubbleSorter<T> extends CustomSorter<T> {
    public BubbleSorter(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    protected void sortImpl(List<T> list) {
        int size = list.size();

        for (int i = 0; i < size - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < size - i - 1; j++) {
                if (this.comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}
