import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_tree_나무박멸 {
    public static int n, m, k, c;
    public static int answer;
    public static int[][] trees, addTree, herb;
    public static final int[] dx = {-1, 0, 0, 1};
    public static final int[] dy = {0, -1, 1, 0};

    public static final int[] ddx = {-1, -1, 1, 1};
    public static final int[] ddy = {-1, 1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        trees = new int[n][n];
        herb = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                trees[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (m-- > 0) {
            growTree();
            spreadTree();
            decreaseHerb();
            spreadHerb();
        }
        System.out.println(answer);
    }

    private static void decreaseHerb() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (herb[i][j] > 0) herb[i][j] -= 1;
            }
        }
    }

    private static void spreadHerb() {
        int maxCount = 0;
        int maxX = 0, maxY = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (trees[i][j] <= 0) continue;

                int count = trees[i][j];
                for (int d = 0; d < 4; d++) {
                    int nx = i;
                    int ny = j;

                    for (int x = 0; x < k; x++) {
                        nx += ddx[d];
                        ny += ddy[d];

                        if (!isRange(nx,ny) || trees[nx][ny] <= 0) break;
                        count += trees[nx][ny];
                    }
                }

                if (maxCount < count) {
                    maxCount = count;
                    maxX = i;
                    maxY = j;
                }
            }
        }
        answer += maxCount;

        if (trees[maxX][maxY] > 0) {
            trees[maxX][maxY] = 0;
            herb[maxX][maxY] = c;

            for (int d = 0; d < 4; d++) {
                int nx = maxX;
                int ny = maxY;

                for (int x = 0; x < k; x++) {
                    nx += ddx[d];
                    ny += ddy[d];

                    if (!isRange(nx, ny) || trees[nx][ny] == -1) break;
                    if (trees[nx][ny] == 0) {
                        herb[nx][ny] = c;
                        break;
                    }
                    trees[nx][ny] = 0;
                    herb[nx][ny] = c;
                }
            }
        }
    }

    private static void spreadTree() {
        addTree = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (trees[i][j] <= 0) continue;

                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (!isRange(nx,ny) || herb[nx][ny] > 0) continue;
                    if (trees[nx][ny] == 0) count += 1;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (!isRange(nx,ny) || herb[nx][ny] > 0) continue;
                    if (trees[nx][ny] == 0) addTree[nx][ny] += trees[i][j] / count;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trees[i][j] += addTree[i][j];
            }
        }
    }

    private static void growTree() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (trees[i][j] <= 0) continue;

                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (!isRange(nx,ny)) continue;
                    if (trees[nx][ny] > 0) count += 1;
                }

                trees[i][j] += count;
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
