import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_BOJ_24337_가희와_탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        boolean flag = false;
        Deque<Integer> q = new LinkedList<>();

        if(a == 1 || b == 1) flag = true;
        if (a + b == 2) for (int i = 1; i <= N; i++) q.add(1);
        else if (a + b <= N + 1 && flag) oneContains(a, b, N, q);
        else if (a + b <= N + 1 && !flag) noOneContains(a, b, N, q);
        else {
            System.out.println(-1);
            return;
        }

        for (int answer : q) System.out.print(answer + " ");
        System.out.println();
    }

    private static void noOneContains(int a, int b, int N, Deque<Integer> q) {
        if (a >= b) {
            for (int i = a; i >= 1; i--) q.addFirst(i);
            for (int i = b - 1; i >= 1; i--) q.addLast(i);

            int diff = N - q.size();
            while (diff-- > 0) q.addFirst(1);
        } else {
            for (int i = b; i >= 1; i--) q.addLast(i);
            for (int i = a - 1; i >= 1; i--) q.addFirst(i);

            int diff = N - q.size();
            while (diff-- > 0) q.addFirst(1);
        }
    }

    private static void oneContains(int a, int b, int N, Deque<Integer> q) {
        int diff;

        if (a != 1) {
            diff = N - a;
            for (int i = 1; i <= a; i++) q.addLast(i);
            while (diff-- > 0) q.addFirst(1);
        }

        if (b != 1) {
            diff = N - b;
            for (int i = b; i >= 1; i--) {
                q.addLast(i);

                if (i == b) {
                    while (diff-- > 0) q.addLast(1);
                }
            }
        }
    }
}
