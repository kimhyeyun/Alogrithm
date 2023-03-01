import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_13787_Infinity_Maze {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N, M, sx, sy, sd;
    static long L, T;
    static char[][] maze;
    static long[][][] dp;
    static final String[] dir = {"E", "S", "W", "N"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Long.parseLong(st.nextToken());

            if (N == 0 && M == 0 && L == 0) break;

            int[] now = new int[3];
            dp = new long[4][N][M];
            maze = new char[N][M];
            T = 0;

            for (int i = 0; i < N; i++) {
                maze[i] = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    if (maze[i][j] == 'E') now = new int[]{i, j, 0};
                    else if (maze[i][j] == 'S') now = new int[]{i, j, 1};
                    else if (maze[i][j] == 'W') now = new int[]{i, j, 2};
                    else if (maze[i][j] == 'N') now = new int[]{i, j, 3};
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < N; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }

            DFS(now[0], now[1], now[2], 0);

            if (T == 0)
                sb.append(sx + 1).append(" ").append(sy + 1).append(" ").append(dir[sd]).append("\n");
            else {
                int x = sx;
                int y = sy;
                int d = sd;

                if ((L - dp[sd][sx][sy]) % T == 0) {
                    for (long i = 0; i < T; i++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        int nd = d;

                        if (nx < 0 || ny < 0 || N <= nx || M <= ny || maze[nx][ny] == '#') {
                            d = (nd + 1) % 4;
                            i -= 1;
                        } else {
                            x = nx;
                            y = ny;
                            d = nd;
                        }
                    }
                    sb.append(x + 1).append(" ").append(y + 1).append(" ").append(dir[d]).append("\n");
                    continue;
                }

                for (long i = 0; i < (L - dp[sd][sx][sy]) % T; i++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    int nd = d;

                    if (nx < 0 || ny < 0 || N <= nx || M <= ny || maze[nx][ny] == '#') {
                        d = (nd + 1) % 4;
                        i -= 1;
                    } else {
                        x = nx;
                        y = ny;
                        d = nd;
                    }
                }
                sb.append(x + 1).append(" ").append(y + 1).append(" ").append(dir[d]).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void DFS(int x, int y, int dir, long t) {
        if (dp[dir][x][y] != -1) {
            sx = x;
            sy = y;
            sd = dir;
            T = t - dp[dir][x][y];
            return;
        }

        dp[dir][x][y] = t;

        if (t == L) {
            sx = x;
            sy = y;
            sd = dir;
            return;
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx < 0 || ny < 0 || N <= nx || M <= ny || maze[nx][ny] == '#') {
            dir = (dir + 1) % 4;
            DFS(x, y, dir, t);
        } else DFS(nx, ny, dir, t + 1);
    }
}
