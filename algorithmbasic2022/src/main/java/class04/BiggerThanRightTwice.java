package class04;

public class BiggerThanRightTwice {

    public static int reversePairs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int ans = 0;
        int windowR = m + 1;

        for (int i = l; i <= m; i++) {
            while (windowR <= r && (long) arr[i] > (long) arr[windowR] * 2) {
                windowR++;
            }
            ans += windowR - m - 1;
        }
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;

        while (p1 < m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= m) {
            help[i++] = arr[p1++];
        }

        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return ans;
    }
}
