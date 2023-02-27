package class03;

/**
 * 在链表中删除指定值的所有节点
 */
public class Code02_DeleteGivenValue {


    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node removeValue(Node head, int num) {
        //从head来到第一个不需要删除的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }

        Node pre = head;
        Node cur = head;

        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            }else{
                pre =cur;
            }
            cur = cur.next;
        }
        return head;
    }

}
