package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CoverMax {

    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int maxCover1(int[][] lines) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    public static int maxCover2(int[][] m) {
        Line[] lines = new Line[m.length];

        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, new StartComparator());

        int max = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < lines.length; i++) {
            // lines[i] -> cur 在黑盒中，把<=cur.start 东西都弹出
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    public static int maxCover3(int[][] m){
        Arrays.sort(m,(a,b) ->(a[0] - b[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max =0;
        for (int [] line : m ){
            while (!heap.isEmpty() && heap.peek() <= line[0]){
                heap.poll();
            }
            heap.add(line[1]);
            max = Math.max(max, heap.size());
        }
        return max;
    }

}
