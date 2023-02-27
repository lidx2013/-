package class10;

import java.util.Stack;

public class Code03_UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void pre(Node head) {
        System.out.print("pre-order: ");
        if (head!=null){
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while (!stack.isEmpty()){
                head = stack.pop();
                System.out.println(head.value + " ");

                if (head.right != null) {
                    stack.push(head.right);
                }

                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }

    public static void in(Node cur){
        System.out.print("in-order: ");
        if (cur != null) {
            Stack<Node> stack = new Stack<>();

            while (!stack.isEmpty() || cur != null) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }else{
                    cur = stack.pop();
                    System.out.print(cur.value + " ");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }


    public static void post(Node head){
        System.out.print("pos-order: ");
        if (head != null) {

            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);

            while (!s1.isEmpty()) {
                head = s1.pop(); // 头 右 左
                s2.push(head);

                if (head.left != null) {
                    s1.push(head.left);
                }

                if (head.right != null) {
                    s1.push(head.right);
                }
            }

            // 左 右 头
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }

        }
    }
}
