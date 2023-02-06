import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1976_여행_가자 {
    static int N, M;
    static List<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        List<Integer> plan = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            plan.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M - 1; i++) {
            if (plan.get(i) == plan.get(i + 1)) continue;
            if (!isPossible(plan.get(i), plan.get(i + 1))) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    private static boolean isPossible(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisited = new boolean[N + 1];

        q.add(start);
        isVisited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : adj[now]) {
                if (next == end) {
                    return true;
                }
                if (!isVisited[next]) {
                    q.add(next);
                    isVisited[next] = true;
                }
            }
        }
        return false;
    }
}
