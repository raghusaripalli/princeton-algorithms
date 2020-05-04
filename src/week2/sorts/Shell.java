package week2.sorts;

public class Shell {

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && SortHelper.less(a[j], a[j - h]); j -= h)
                    SortHelper.exch(a, j, j - h);
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        Comparable[] arr = {2, 3, 8, 1, 4, 10, 7, 6, 5, 9};
        Shell.sort(arr);
        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
