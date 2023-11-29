import java.util.HashSet;
import java.util.Set;

public class task4 {
    static class Point2D {
        public int x;
        public int y;

        public Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] selPoint;
    static boolean[] isVisitied;
    static int ans = 0;
    static Set<int[]> set;
    public static int solution(Point2D[] A) {
        // write your code in Java SE 8

        selPoint = new int[3];
        isVisitied = new boolean[A.length];
        set = new HashSet<>();
        choicePoint(0,0, A);

        if(ans > 100000000) return -1;
        else return ans;
    }

    private static void choicePoint(int cnt, int idx, Point2D[] a) {
        if (cnt ==  3) {
            if(set.contains(new int[]{selPoint[0], selPoint[1], selPoint[2]})) return;

            Point2D one = a[selPoint[0]];
            Point2D two = a[selPoint[1]];
            Point2D three = a[selPoint[2]];

            int t = one.x * (two.y - three.y) + two.x * (three.y - one.y) + three.x * (one.y - two.y);
            if (t == 0) {
                ans += 1;
                set.add(new int[]{selPoint[0], selPoint[1], selPoint[2]});
            }

            return;
        }

        for (int i = idx; i < a.length; i++) {
            if (!isVisitied[i]) {
                isVisitied[i] = true;
                selPoint[cnt] = i;
                choicePoint(cnt + 1, i+1, a);
                isVisitied[i] = false;
            }

        }
    }

    public static void main(String[] args) {
        Point2D[] a = {new Point2D(0, 0), new Point2D(1, 1), new Point2D(2, 2), new Point2D(3, 3), new Point2D(3, 2), new Point2D(4, 2), new Point2D(5, 1)};
        System.out.println(solution(a));
    }
}
