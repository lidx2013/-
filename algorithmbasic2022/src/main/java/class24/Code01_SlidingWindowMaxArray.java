package class24;

import java.util.LinkedList;

/**
 * 窗口内最大值或最小值更新结构的实现
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class Code01_SlidingWindowMaxArray {

    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;

        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int R = 0; R < arr.length; R++) {
            //保证是最大的入队
            while (!queue.isEmpty() && arr[queue.getLast()] < arr[R]) {
                queue.pollLast();
            }
            queue.addLast(R);
            //
            if (R - w == queue.peekFirst()) {
                queue.pollFirst();
            }
            if (R >= w - 1) {
                res[index++] = arr[queue.peekFirst()];
            }
        }
        return res;
    }
}
