package class12;

public class Code04_IsFull {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public static boolean isFull(Node head){
        if (head==null){
            return true;
        }
        return process(head).isFull;
    }


    public static class Info{
        public boolean isFull;
        public int height;
        public Info (boolean f , int h ){
            isFull= f;
            height= h;
        }
    }
    private static Info process(Node head) {
        if (head == null ){
            return new Info (true,0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(isFull, height);
    }


}
