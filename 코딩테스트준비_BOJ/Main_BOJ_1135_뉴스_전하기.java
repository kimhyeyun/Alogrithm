import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1135_뉴스_전하기 {
    static int N;
    static List<Integer>[] organize;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        organize = new List[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            organize[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 1; i < N; i++) {
            organize[Integer.parseInt(st.nextToken())].add(i);
        }

        System.out.println(DFS(0));
    }

    private static int DFS(int now) {
        int cnt = 0, max = 0;

        Queue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

        for (int next : organize[now]) {
            dp[next] = DFS(next);
            q.add(new int[]{next, dp[next]});
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            cnt += 1;

            max = Math.max(max, cur[1] + cnt);
        }
        return max;
    }
}
