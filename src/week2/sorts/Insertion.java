package week2.sorts;

public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (SortHelper.less(a[j], a[j - 1]))
                    SortHelper.exch(a, j, j - 1);
                else break;
            }
        }
    }

    public static void main(String[] args) {
        Comparable[] arr = {2, 3, 8, 1, 4, 10, 7, 6, 5, 9};
        Insertion.sort(arr);
        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
