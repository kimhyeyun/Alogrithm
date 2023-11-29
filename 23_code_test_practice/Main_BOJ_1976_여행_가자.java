import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1976_여행_가자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Integer>[] adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j<N;j++) {
                int d = Integer.parseInt(st.nextToken());
                if (d == 0) continue;

                adj[i + 1].add(j + 1);
                adj[j + 1].add(i + 1);
            }
        }

        st = new StringTokenizer(br.readLine());
        List<Integer> plan = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            plan.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M - 1; i++) {
            if(Objects.equals(plan.get(i), plan.get(i + 1))) continue;
            if(!isPossible(plan.get(i), plan.get(i+1), adj, N)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static boolean isPossible(int start, int end, List<Integer>[] adj, int N) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisited = new boolean[N + 1];

        q.add(start);
        isVisited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adj[cur]) {
                if(next == end) return true;
                if(isVisited[next]) continue;

                q.add(next);
                isVisited[next] = true;
            }
        }
        return false;
    }
}
