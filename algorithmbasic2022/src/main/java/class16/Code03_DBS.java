package class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * 图深度遍历
 * 一条到走到黑，然后在往上走
 */
public class Code03_DBS {


    public static void dfs (Node node){

        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();

        stack.add(node);
        set.add(node);

        System.out.println(node.value);

        while (! stack.isEmpty()){
            Node cur = stack.pop();

            for (Node next : cur.nexts){
                if (!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }

    }

}
