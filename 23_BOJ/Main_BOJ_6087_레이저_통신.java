import javax.swing.plaf.IconUIResource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BOJ_6087_레이저_통신 {

    static int N, M, answer;
    static char[][] map;
    static int[][][] isVisited;
    static int[] start;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'C') start = new int[]{i, j};
            }
        }

        map[start[0]][start[1]] = '.';
        answer = Integer.MAX_VALUE;

        BFS();

        System.out.println(answer);
    }

    private static void BFS() {
        PriorityQueue<Point> q = new PriorityQueue<>();
        isVisited = new int[N][M][4];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(isVisited[i][j], Integer.MAX_VALUE);
            }
        }

        q.add(new Point(start[0], start[1], -1, -1));

        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (map[cur.x][cur.y] == 'C') {
                answer = Math.min(answer, cur.count);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                int nc = (cur.dir == d) ? cur.count : cur.count + 1;

                if(nx < 0 || ny < 0 || N <= nx || M <= ny || isVisited[nx][ny][d] <= nc) continue;
                if(map[nx][ny] == '*' || Math.abs(cur.dir - d) == 2) continue;

                isVisited[nx][ny][d] = nc;
                q.add(new Point(nx, ny, d, nc));
            }
        }
    }

    private static class Point implements Comparable<Point> {
        int x, y;
        int dir, count;

        public Point(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) {
            return this.count - o.count;
        }
    }


}