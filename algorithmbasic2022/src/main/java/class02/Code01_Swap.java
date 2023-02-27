package class02;

/**
 * eg1.不用额外变量交互2个数的值
 * A ^ 0  = A
 * A ^ A = 0
 */
public class Code01_Swap {

    public static void swap(int a, int b) {

        System.out.println(a + " ,  " + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a + " ,  " + b);
    }

    public static void main(String[] args) {
        swap(0,3);
    }
}
