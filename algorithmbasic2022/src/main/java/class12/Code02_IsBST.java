package class12;

/**
 * 判断二叉树是不是平衡二叉树
 */
public class Code02_IsBST {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int ma, int mi) {
            this.isBST = isBST;
            max = ma;
            min = mi;
        }
    }


    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int max = x.value;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo!=null){
            max = Math.max(max,rightInfo.max );
        }

        int min = x.value;

        if (leftInfo!= null ){
            min = Math.min(min , leftInfo.min);
        }
        if (rightInfo!=null){
            min = Math.min(min,rightInfo.min);
        }

        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST){
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }

        if (leftInfo != null && leftInfo.max >= x.value) {
            isBST = false;
        }

        if (rightInfo != null && rightInfo.min <= x.value) {
            isBST = false;
        }

        return new Info(isBST, max, min);
    }
}
