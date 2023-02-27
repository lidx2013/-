package class02;

import java.util.HashMap;
import java.util.HashSet;

/**
 * // 输入一定能够保证，数组中所有的数都出现了M次，只有一种数出现了K次
 * // 1 <= K < M
 * // 返回这种数
 */
public class Code03_KM {

    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int ans = 0;
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    public static int[] randomArray(int maxKinds,int range,int k ,int m){
        int ktimeNum = randomNumber(range);

        int times = k ;
        int numKinds = (int)(Math.random()* maxKinds) +2 ;

        int[] arr = new int [ times + (numKinds-1) * m];

        int index =0 ;
        for (;index<times;index++){
            arr[index] = ktimeNum;
        }
        numKinds--;

        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);

        while (numKinds!=0){
            int curNum= 0;
            do{
                curNum = randomNumber(range);
            }while (set.contains(curNum));
            set.add(curNum);
            numKinds--;

            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        //随机排序
        for(int i =0 ; i< arr.length;i++){
            int j = (int)(Math.random()*arr.length);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j]=temp;
        }
        return arr;
    }

    /**
     * 随机数[-range,range]
     * @param range
     * @return
     */
    public static int randomNumber(int range){
        return (int)(Math.random()*(range+1)) - (int)(Math.random()*(range+1));
    }

    public static int km(int[] arr, int k, int m) {
        int[] help = new int[32];
        //统计32位中每个位数的累计
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                help[i] += (num >> (i)) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            //看是否是m的整数倍
            help[i] = m % help[i];
            if (help[i] != 0) {
                //说明K在i位置上是1，需要把1设置进去
                ans |= 1 << i;
            }
        }
        return ans;
    }


    // 为了测试
    public static void main(String[] args) {
        int kinds = 5;
        int range = 30;
        int testTime = 100000;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
            int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            // k < m
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k, m);
            //int ans2 = onlyKTimes(arr, k, m);
            int ans3 = km(arr, k, m);
           // if (ans1 != ans2 || ans1 != ans3) {
            if (ans1 != ans3) {
                System.out.println(ans1);
                System.out.println(ans3);
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
