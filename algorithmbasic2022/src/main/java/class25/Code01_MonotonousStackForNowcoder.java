package class25;

/**
 * 给定一个可能含有重复值的数组 arr，找到每一个 i 位置左边和右边离 i 位置最近且值比 arr[i] 小的位置。返回所有位置相应的信息。
 */
public class Code01_MonotonousStackForNowcoder {

    public static int[] stack1 = new int[1000000];
    public static int[] stack2 = new int[1000000];

    public static int[] arr = new int[1000000];
    public static int[][] ans = new int[1000000][2];


    public static void getNearLess(int n) {
        int stackSize1 = 0;
        int stackSize2 = 0;

        for (int i = 0; i < n; i++) {

            while (stackSize1 > 0 && arr[stackSize1 - 1] > arr[i]) {
                int curIndex = stack1[--stackSize1];
                int left = stackSize2 < 2 ? -1 : stack2[stackSize2 - 2];

                ans[curIndex][0] = left;
                ans[curIndex][1] = i;

                if (stackSize1 == 0 || arr[stack1[stackSize1 - 1]] != arr[curIndex]) {
                    stackSize2--;
                }
            }
            if (stackSize1 != 0 && arr[stack1[stackSize1 - 1]] == arr[i]) {
                stack2[stackSize2 - 1] = i;
            } else {
                stack2[stackSize2++] = i;
            }
            stack1[stackSize1++] = i;
        }

        while (stackSize1 != 0) {
            int curIndex = stack1[--stackSize1];
            int left = stackSize2 < 2 ? -1 : stack2[stackSize2 - 2];
            ans[curIndex][0] = left;
            ans[curIndex][1] = -1;
            if (stackSize1 == 0 || arr[stack1[stackSize1 - 1]] != arr[curIndex]) {
                stackSize2--;
            }
        }

    }
}
