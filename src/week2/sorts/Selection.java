package week2.sorts;

public class Selection {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (SortHelper.less(a[j], a[min]))
                    min = j;
            }
            SortHelper.exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Comparable[] arr = {2, 3, 8, 1, 4, 10, 7, 6, 5, 9};
        Selection.sort(arr);
        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
