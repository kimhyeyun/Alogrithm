import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_tree_예술성 {
    static int n, countOfGroup;
    static int[][] picture, group, next;
    static int[] countOfPixel;
    static boolean[][] isVisited;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        picture = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                picture[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            answer += getScore();
            rotate();
        }

        System.out.println(answer);
    }

    private static void rotate() {
        next = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == n / 2) next[i][j] = picture[j][n - i - 1];
                else if (i == n / 2) next[i][j] = picture[j][i];
            }
        }

        int squareN = n / 2;
        rotateSquare(0, 0, squareN);
        rotateSquare(0, squareN + 1, squareN);
        rotateSquare(squareN + 1, 0, squareN);
        rotateSquare(squareN + 1, squareN + 1, squareN);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                picture[i][j] = next[i][j];
            }
        }

    }

    private static void rotateSquare(int sx, int sy, int size) {
        for (int x = sx; x < sx + size; x++) {
            for (int y = sy; y < sy + size; y++) {
                int ox = x - sx, oy = y - sy;
                int rx = oy, ry = size - ox - 1;
                next[rx + sx][ry + sy] = picture[x][y];
            }
        }
    }

    private static int getScore() {
        makeGroup();
        return getArtScore();
    }

    private static int getArtScore() {
        int score = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (inRange(nx, ny) && picture[nx][ny] != picture[i][j]) {
                        int g1 = group[i][j];
                        int g2 = group[nx][ny];
                        int num1 = picture[i][j];
                        int num2 = picture[nx][ny];
                        int count1 = countOfPixel[g1];
                        int count2 = countOfPixel[g2];

                        score += (count1 + count2) * num1 * num2;
                    }
                }
            }
        }
        return score / 2;
    }

    private static void makeGroup() {
        countOfGroup = 0;
        isVisited = new boolean[n][n];
        group = new int[n][n];
        countOfPixel = new int[n * n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isVisited[i][j]) continue;
                countOfGroup += 1;
                isVisited[i][j] = true;
                group[i][j] = countOfGroup;
                countOfPixel[countOfGroup] = 1;
                DFS(i, j);
            }
        }

    }

    private static void DFS(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!inRange(nx,ny) || isVisited[nx][ny] || picture[nx][ny] != picture[x][y]) continue;
            isVisited[nx][ny] = true;
            group[nx][ny] = countOfGroup;
            countOfPixel[countOfGroup] += 1;
            DFS(nx, ny);
        }
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
