import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1260_DFS와_BFS {
    static String answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        List<Integer>[] adj = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adj[x].add(y);
            adj[y].add(x);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }

        answer = "";
        DFS(V, adj, new boolean[N + 1], String.valueOf(V));
        System.out.println(answer);

        answer = "";
        BFS(N, V, adj);
        System.out.println(answer);
    }

    private static void BFS(int N, int start, List<Integer>[] adj) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisited = new boolean[N + 1];

        isVisited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            answer += cur + " ";

            for (int next : adj[cur]) {
                if(isVisited[next]) continue;

                q.add(next);
                isVisited[next] = true;
            }
        }
    }

    private static void DFS(int cur, List<Integer>[] adj, boolean[] isVisited, String list) {
        isVisited[cur] = true;

        answer += cur + " ";
        for (int next : adj[cur]) {
            if (isVisited[next]) continue;
            DFS(next, adj, isVisited, list + " " + next);
        }
    }
}
