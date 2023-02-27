package class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code5_TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        // 当前你正在统计哪一层的宽度
        int curLevel = 1;

        // 当前层curLevel层，宽度目前是多少
        int curLevelNodes = 0;
        int max = 0;

        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);

        while (!queue.isEmpty()) {

            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }

            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

}
