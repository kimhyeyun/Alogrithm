import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_17472_다리_만들기_2 {

    static class Node implements Comparable<Node>{
        int x, y, d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }

    static Queue<Node> q;
    static List<Node> bridge;
    static int[] parent;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[][] map;
    static boolean[][] isVisited;
    static int N, M, label;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        label = 0;
        map = new int[N][M];
        isVisited = new boolean[N][M];

        q = new LinkedList<>();
        bridge = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !isVisited[i][j]) {
                    label += 1;
                    isVisited[i][j] = true;
                    map[i][j] = label;
                    q.offer(new Node(i, j, 0));
                    labelIsland();
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    q.offer(new Node(i, j, 0));
                    calDistance(map[i][j]);
                }
            }
        }

        parent = new int[label + 1];
        for (int i = 1; i < label + 1; i++) {
            parent[i] = i;
        }

        kruskal();
    }

    private static void kruskal() {
        Collections.sort(bridge);

        int cnt = 0, dist = 0;
        for (Node node : bridge) {
            if (find(node.x) != find(node.y)) {
                union(node.x, node.y);
                dist += node.d;
                cnt += 1;
            }
        }
        if(cnt != label-1) System.out.println(-1);
        else System.out.println(dist);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        parent[rootB] = rootA;
    }

    private static int find(int a) {
        if(parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    private static void calDistance(int start) {
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                int dist = 0;
                boolean flag = false;

                while (true) {
                    if (nx < 0 || ny < 0 || N <= nx || M <= ny || map[nx][ny] == start) break;

                    if (map[nx][ny] != -1) {
                        flag = true;
                        break;
                    }

                    dist += 1;

                    nx += dx[i];
                    ny += dy[i];
                }

                if (flag) {
                    if(dist < 2) continue;
                    bridge.add(new Node(start, map[nx][ny], dist));
                }
            }
        }
        q.clear();
    }

    private static void labelIsland() {
        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny || isVisited[nx][ny]) continue;

                if (map[nx][ny] == 0) {
                    isVisited[nx][ny] = true;
                    map[nx][ny] = label;
                    q.offer(new Node(nx, ny, 0));
                }
            }
        }

        q.clear();
    }
}
