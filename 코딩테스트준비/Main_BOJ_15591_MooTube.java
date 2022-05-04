import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_15591_MooTube {
    static int N, Q;
    static List<int[]>[] USADO;
    static PriorityQueue<Integer> q;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        Q = Integer.parseInt(stringTokenizer.nextToken());

        USADO = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            USADO[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());

            USADO[a].add(new int[]{b, d});
            USADO[b].add(new int[]{a, d});
        }

        while (Q-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());

            q = new PriorityQueue<>();
            isVisited = new boolean[N + 1];

            isVisited[v] = true;
            q.add(v);
            int cnt = 0;

            while (!q.isEmpty()) {
                int now = q.poll();

                for (int[] next : USADO[now]) {
                    if (!isVisited[next[0]] && k <= next[1]) {
                        q.offer(next[0]);
                        isVisited[next[0]] = true;
                        cnt += 1;
                    }
                }
            }

            System.out.println(cnt);
        }
    }
}
