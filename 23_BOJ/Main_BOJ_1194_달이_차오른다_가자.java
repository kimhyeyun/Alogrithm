import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1194_달이_차오른다_가자 {
    static int N, M, answer;
    static Node start;
    static char[][] map;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if(map[i][j] == '0') start = new Node(i, j, 0, 0);
            }
        }

        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<Node> q = new LinkedList<>();
        boolean[][][] isVisited = new boolean[N][M][64];

        q.add(start);
        isVisited[start.x][start.y][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if(map[now.x][now.y] == '1') return now.dist;

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                if(isVisited[nx][ny][now.key] || map[nx][ny] == '#') continue;

                if ('a' <= map[nx][ny] && map[nx][ny] <= 'f') {
                    int nkey = 1 << (map[nx][ny] - 'a');
                    nkey = now.key | nkey;
                    isVisited[nx][ny][nkey] = true;
                    q.add(new Node(nx, ny, now.dist + 1, nkey));
                } else if ('A' <= map[nx][ny] && map[nx][ny] <= 'F') {
                    if ((now.key & 1 << (map[nx][ny] - 'A')) == (int) Math.pow(2, map[nx][ny] - 'A')) {
                        isVisited[nx][ny][now.key] = true;
                        q.add(new Node(nx, ny, now.dist + 1, now.key));
                    }
                } else {
                    isVisited[nx][ny][now.key] = true;
                    q.add(new Node(nx, ny, now.dist + 1, now.key));
                }
            }
        }
        return -1;
    }

    static class Node {
        int x, y;
        int dist, key;

        public Node(int x, int y, int dist, int key) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.key = key;
        }
    }
}
