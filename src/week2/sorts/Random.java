package week2.sorts;

import edu.princeton.cs.algs4.StdRandom;

public class Random {
    public static void knuthShuffle(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniform(i);
            SortHelper.exch(a, i, r);
        }
    }
}
