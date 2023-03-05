import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_11965_Bessies_Dream {
    static class Cell implements Comparable<Cell>{
        int x, y, d, s;

        public Cell(int x, int y, int d, int s) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
        }

        @Override
        public int compareTo(Cell o) {
            return this.d - o.d;
        }
    }

    static final int SMELL = 1, NON_SMELL = 0;
    static final int RED = 0, PINK = 1, ORANGE = 2, BLUE = 3, PURPLE = 4;
    static int N, M;
    static int[][] maze;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(BFS(0, 0, NON_SMELL));

    }

    private static int BFS(int x, int y, int isSmell) {
        PriorityQueue<Cell> q = new PriorityQueue<>();
        int[][][] minDist = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(minDist[i][j], Integer.MAX_VALUE);
            }
        }

        q.add(new Cell(x, y, 0, isSmell));
        minDist[x][y][isSmell] = 0;

        while (!q.isEmpty()) {
            Cell now = q.poll();

            if(now.x == N -1 && now.y == M - 1) return now.d;
            
            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                int isSmelled = now.s;
                int dist = now.d + 1;

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                if(maze[nx][ny] == RED) continue;
                if(maze[nx][ny] == BLUE && isSmelled == NON_SMELL) continue;

                if (maze[nx][ny] == PURPLE) {
                    isSmelled = NON_SMELL;

                    while (true) {
                        nx += dx[d];
                        ny += dy[d];
                        dist += 1;

                        if (nx < 0 || ny < 0 || N <= nx || M <= ny || maze[nx][ny] == RED || maze[nx][ny] == BLUE) {
                            nx -= dx[d];
                            ny -= dy[d];
                            dist -= 1;
                            break;
                        }
                        if(maze[nx][ny] != PURPLE) break;
                    }
                }

                if(maze[nx][ny] == ORANGE) isSmelled = SMELL;
                if(minDist[nx][ny][isSmelled] <= dist) continue;

                minDist[nx][ny][isSmelled] = dist;
                q.add(new Cell(nx, ny, dist, isSmelled));
            }
        }
        return -1;
    }
}
