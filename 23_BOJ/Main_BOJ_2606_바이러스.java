import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_2606_바이러스 {
    static int N, M;
    static List<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adj[x].add(y);
            adj[y].add(x);
        }

        System.out.println(BFS(1));
    }

    private static int BFS(int start) {
        int count = 0;

        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisited = new boolean[N + 1];

        q.add(start);
        isVisited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adj[cur]) {
                if(isVisited[next]) continue;

                count += 1;
                q.add(next);
                isVisited[next] = true;
            }
        }
        return count;
    }
}
