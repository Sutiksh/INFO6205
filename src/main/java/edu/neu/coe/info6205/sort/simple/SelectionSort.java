package edu.neu.coe.info6205.sort.simple;

public class SelectionSort<X extends Comparable<X>> implements Sort<X> {

    /**
     * Constructor for SelectionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public SelectionSort(Helper<X> helper) {
        this.helper = helper;
    }

    public SelectionSort() {
        this(new Helper<>("SelectionSort"));
    }

    @Override
    public void sort(X[] xs, int from, int to) {
        // TO BE IMPLEMENTED ...
        for (int i = from; i < to; i++) {
            int min = i;
            for (int j = i + 1; j < to; j++)
                if (helper.less(xs[j], xs[min]))
                    min = j;
            helper.swap(xs, from, to, i, min);
        }
        // ... END IMPLEMENTATION
    }

    @Override
    public String toString() {
        return helper.toString();
    }

    public Helper<X> getHelper() {
        return helper;
    }

    private final Helper<X> helper;
}
