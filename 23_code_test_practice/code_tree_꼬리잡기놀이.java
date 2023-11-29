import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class code_tree_꼬리잡기놀이 {
    public static int n, m, k;
    public static int answer;
    public static int[][] map;
    public static List<int[]>[] v;
    public static int[] tail;
    public static boolean[][] isVisited;
    public static int[][] indexOfrails;
    public static final int[] dx = {-1, 0, 0, 1};
    public static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i <= k; i++) {
            move();

            int gotBallIndex = throwBall(i);
            reverse(gotBallIndex);
        }

        System.out.println(answer);
    }

    private static void reverse(int index) {
        if (index == 0) return;

        List<int[]> newV = new ArrayList<>();
        for (int i = tail[index] - 1; i >= 0; i--) {
            int[] pos = v[index].get(i);
            newV.add(pos);
        }

        for (int i = v[index].size() - 1; i >= tail[index]; i--) {
            newV.add(v[index].get(i));
        }

        v[index] = newV;
        for (int j = 0; j < v[index].size(); j++) {
            int[] pos = v[index].get(j);

            if (j == 0) map[pos[0]][pos[1]] = 1;
            else if (j < tail[index] - 1) map[pos[0]][pos[1]] = 2;
            else if (j == tail[index] - 1) map[pos[0]][pos[1]] = 3;
            else map[pos[0]][pos[1]] = 4;
        }
    }

    private static int throwBall(int turn) {
        int t = (turn - 1) % (4 * n) + 1;

        if (t <= n) {
            for (int i = 0; i < n; i++) {
                if (1 <= map[t - 1][i] && map[t - 1][i] <= 3) {
                    getPoint(t - 1, i);
                    return indexOfrails[t - 1][i];
                }
            }
        } else if (t <= 2 * n) {
            t -= n;
            for (int i = 0; i < n; i++) {
                if (1 <= map[n - 1 - i][t - 1] && map[n - 1 - i][t - 1] <= 3) {
                    getPoint(n - 1 - i, t - 1);
                    return indexOfrails[n - 1 - i][t - 1];
                }
            }

        } else if (t <= 3 * n) {
            t -= (2 * n);
            for (int i = 0; i < n; i++) {
                if (1 <= map[n - t][n - 1 - i] && map[n - t][n - 1 - i] <= 3) {
                    getPoint(n - t, n - 1 - i);
                    return indexOfrails[n - t][n - 1 - i];
                }
            }
        } else {
            t -= (3 * n);
            for (int i = 0; i < n; i++) {
                if (1 <= map[i][n - t] && map[i][n - t] <= 3) {
                    getPoint(i, n - t);
                    return indexOfrails[i][n - t];
                }
            }
        }
        return 0;
    }

    private static void getPoint(int x, int y) {
        int index = indexOfrails[x][y];
        int count = 0;
        for (int i = 0; i < v[index].size(); i++) {
            if (v[index].get(i)[0] == x && v[index].get(i)[1] == y) {
                count = i;
            }
        }
        answer += Math.pow(count + 1, 2);
    }

    private static void move() {
        for (int i = 1; i <= m; i++) {
            int[] tmp = v[i].get(v[i].size() - 1);
            for (int j = v[i].size() - 1; j >= 1; j--) {
                v[i].set(j, v[i].get(j - 1));
            }
            v[i].set(0, tmp);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < v[i].size(); j++) {
                int[] pos = v[i].get(j);

                if (j == 0) map[pos[0]][pos[1]] = 1;
                else if (j < tail[i] - 1) map[pos[0]][pos[1]] = 2;
                else if (j == tail[i] - 1) map[pos[0]][pos[1]] = 3;
                else map[pos[0]][pos[1]] = 4;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        v = new ArrayList[m + 1];
        isVisited = new boolean[n][n];
        indexOfrails = new int[n][n];

        tail = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            v[i] = new ArrayList<>();
        }

        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) v[count++].add(new int[]{i, j});
            }
        }

        for (int i = 1; i <= m; i++) {
            DFS(v[i].get(0)[0], v[i].get(0)[1], i);
        }
    }

    private static void DFS(int x, int y, int index) {
        isVisited[x][y] = true;
        indexOfrails[x][y] = index;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!isRange(nx,ny) || map[nx][ny] == 0 || isVisited[nx][ny]) continue;
            if (v[index].size() == 1 && map[nx][ny] != 2) continue;

            v[index].add(new int[]{nx, ny});
            if (map[nx][ny] == 3) tail[index] = v[index].size();

            DFS(nx, ny, index);
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
