import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_BOJ_24337_가희와_탑 {
    static int N, a, b;
    static boolean flag;
    static Deque<Integer> dq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        a = Integer.parseInt(stringTokenizer.nextToken());
        b = Integer.parseInt(stringTokenizer.nextToken());

        flag = false;
        dq = new LinkedList<>();

        if (a == 1 || b == 1) {
            flag = true;
        }

        if (a + b == 2) {
            for (int i = 0; i < N; i++) {
                dq.add(1);
            }
        } else if (a + b <= N + 1 && flag) {
            oneContains();
        } else if (a + b <= N + 1 && !flag) {
            noOneContains();
        } else {
            System.out.println(-1);
            return;
        }

        for (int ans : dq) {
            System.out.print(ans + " ");
        }
        System.out.println();
    }

    private static void noOneContains() {
        if (a >= b) {
            for (int i = a; i >= 1; i--) {
                dq.addFirst(i);
            }
            for (int i = b - 1; i >= 1; i--) {
                dq.addLast(i);
            }
            int gap = N - dq.size();
            for (int i = 1; i <= gap; i++) {
                dq.addFirst(1);
            }
        } else {
            for (int i = b; i >= 1; i--) {
                dq.addLast(i);
            }
            for (int i = a - 1; i >= 1; i--) {
                dq.addFirst(i);
            }
            int gap = N - dq.size();
            for (int i = 0; i < gap; i++) {
                dq.addFirst(1);
            }
        }
    }

    private static void oneContains() {
        int gap;

        if (a != 1) {
            gap = N - a;
            for (int i = 1; i <= a; i++) {
                dq.addLast(i);
            }
            while (gap-- > 0) {
                dq.addFirst(1);
            }
        }

        if (b != 1) {
            gap = N - b;
            for (int i = b; i >= 1; i--) {
                dq.addLast(i);

                if (i == b) {
                    while (gap-- > 0) {
                        dq.addLast(1);
                    }
                }
            }
        }
    }
}
