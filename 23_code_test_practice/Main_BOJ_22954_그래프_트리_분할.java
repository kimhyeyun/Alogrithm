import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_22954_그래프_트리_분할 {
    static List<int[]>[] adj;
    static boolean[] isVisited, isLeaf;
    static List<Integer>[] vertexes, edges;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adj = new List[N + 1];

        edges = new List[N + 1];
        vertexes = new List[N + 1];

        isLeaf = new boolean[N + 1];
        isVisited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            adj[i] = new LinkedList<>();

            edges[i] = new LinkedList<>();
            vertexes[i] = new LinkedList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(new int[]{v, i});
            adj[v].add(new int[]{u, i});
        }

        for (int i = 1; i <= N; i++) {
            if(isVisited[i]) continue;

            DFS(i, size);
            size += 1;
        }
        System.out.println(prints(N));
    }

    private static String prints(int n) {
        if(size > 2) return "-1";
        if(size < 2) decomposition(n);

        if(vertexes[0].size() == vertexes[1].size()) return "-1";

        StringBuilder sb = new StringBuilder();
        sb.append(vertexes[0].size()).append(" ").append(vertexes[1].size()).append("\n");

        for (int i = 0; i < 2; i++) {
            Collections.sort(vertexes[i]);
            Collections.sort(edges[i]);

            for (int vertex : vertexes[i]) sb.append(vertex).append(" ");
            sb.append("\n");

            for (int edge : edges[i]) sb.append(edge).append(" ");
            sb.append("\n");
        }

        return sb.toString();
    }

    private static void decomposition(int n) {
        int leaf = 0;
        int start = 0;

        for (int vertex : vertexes[0]) {
            if(isLeaf[vertex]) leaf = vertex;
            else start = vertex;
        }

        vertexes[0] = new LinkedList<>();
        edges[0] = new LinkedList<>();

        isVisited = new boolean[n + 1];
        isVisited[leaf] = true;

        DFS(start, 0);
        vertexes[1].add(leaf);
    }

    private static void DFS(int cur, int index) {
        isVisited[cur] = true;
        vertexes[index].add(cur);

        boolean leaf = true;
        for (int[] next : adj[cur]) {
            if(isVisited[next[0]]) continue;

            edges[index].add(next[1]);
            leaf = false;

            DFS(next[0], index);
        }

        isLeaf[cur] = leaf;
    }
}
