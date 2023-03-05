import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_6087_레이저_통신 {
    static class Point implements Comparable<Point>{
        int x, y;
        int dir;
        int cnt;

        public Point(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    ", cnt=" + cnt +
                    '}';
        }

        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }
    }
    static int N, M;
    static char[][] map;
    static int[][][] isVisited;
    static int[] start;
    static int answer;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'C') {
                    start = new int[]{i, j};
                }
            }
        }

        map[start[0]][start[1]] = '.';

        BFS();

        System.out.println(answer);
    }

    private static void BFS() {
        PriorityQueue<Point> q = new PriorityQueue<>();
        isVisited = new int[N][M][4];

        for (int i = 0; i < N; i++){
            for(int j = 0;j<M;j++) {
                Arrays.fill(isVisited[i][j], Integer.MAX_VALUE);
            }
        }

        q.add(new Point(start[0], start[1], -1, -1));

        while (!q.isEmpty()) {
            Point now = q.poll();

            if(map[now.x][now.y] == 'C'){
                answer = Math.min(answer, now.cnt);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                int nc = (now.dir == d) ? now.cnt : now.cnt + 1;

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                if(map[nx][ny] == '*' || Math.abs(now.dir - d) == 2) continue;

                if(isVisited[nx][ny][d] <= nc) continue;

                isVisited[nx][ny][d] = nc;
                q.add(new Point(nx, ny, d, nc));
            }
        }
    }
}
