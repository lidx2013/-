package class12;

public class Code01_IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //对每一棵只树，是否是满足二叉树、是否是完全二叉树、高度
    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }

    public static Info process(Node X) {
        if (X == null) {
            return new Info(true, true, 0);
        }

        //

        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isFull = leftInfo.isFull &&
                rightInfo.isFull
                && leftInfo.height == rightInfo.height;

        boolean isCBT = false;

        if (isFull) {
            isCBT = true;
        } else {
            if (leftInfo.isCBT && rightInfo.isCBT) {
                if (leftInfo.isCBT
                        && rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }

                if (leftInfo.isFull
                        &&
                        rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }

                if (leftInfo.isFull
                        && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }
            }
        }
        return new Info(isFull, isCBT, height);
    }


    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 10000000;

        for ( int i =0 ; i< testTimes ; i++){
            Node head =generateRandomBST(maxLevel,maxValue);
        }
    }
}
