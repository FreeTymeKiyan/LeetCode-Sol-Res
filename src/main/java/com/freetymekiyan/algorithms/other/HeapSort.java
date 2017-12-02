package com.freetymekiyan.algorithms.other;

/**
 * Heap construction
 * delMax() till empty
 */
public class HeapSort {

    public void sort(Comparable[] pq) {
        int N = pq.length;
        for (int i = N / 2; i >= 1; i--) {
            sink(pq, i, N);
        }

        while (N > 1) {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

    private void exch(Comparable[] pq, int i, int j) {
        Comparable temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void sink(Comparable[] pq, int i, int j) {
        while (2 * i <= j) {
            int k = 2 * i;
            if (k < j && less(pq, k, k + 1)) k++;
            if (!less(pq, i, k)) break;
            exch(pq, i, k);
            i = k;
        }
    }

    private boolean less(Comparable[] pq, int i, int k) {
        return pq[i].compareTo(pq[k]) < 0;
    }

}
