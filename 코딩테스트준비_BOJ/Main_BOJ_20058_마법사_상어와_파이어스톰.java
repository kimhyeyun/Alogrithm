import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_20058_마법사_상어와_파이어스톰 {
    static int N, Q, ans, totalOfIce;
    static int[][] A;
    static int[] L;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        Q = Integer.parseInt(stringTokenizer.nextToken());
        N = (int) Math.pow(2, N);

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        L = new int[Q];
        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            L[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            A = divide(L[i]);
            A = meltIce();
        }

        findLargestIceberg();
        System.out.println(totalOfIce);
        System.out.println(ans);
    }

    private static void findLargestIceberg() {
        boolean[][] isVisited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        ans = 0;
        totalOfIce = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(A[i][j] <= 0 || isVisited[i][j]) continue;

                q.add(new int[]{i, j});
                isVisited[i][j] = true;
                totalOfIce += A[i][j];
                int cnt = 1;
                while (!q.isEmpty()) {
                    int[] now = q.poll();
                    for (int d = 0; d < 4; d++) {
                        int nx = now[0] + dx[d];
                        int ny = now[1] + dy[d];

                        if(nx < 0 || ny < 0 || N <= nx || N <= ny || A[nx][ny] <= 0 || isVisited[nx][ny]) continue;

                        cnt += 1;
                        isVisited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                        totalOfIce += A[nx][ny];
                    }
                }

                ans = ans < cnt ? cnt : ans;
            }
        }

    }

    private static int[][] meltIce() {
        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            tmp[i] = Arrays.copyOf(A[i], N);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                if (A[i][j] == 0) continue;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (nx < 0 || ny < 0 || N <= nx || N <= ny || A[nx][ny] <= 0) continue;

                    cnt += 1;
                }
                if (cnt < 3) tmp[i][j] -= 1;
            }
        }
        return tmp;
    }

    private static int[][] divide(int L) {
        int[][] tmp = new int[N][N];
        L = (int) Math.pow(2, L);

        for (int i = 0; i < N; i += L) {
            for (int j = 0; j < N; j += L) {
                rotate(i, j, L, tmp);
            }
        }

        return tmp;
    }

    private static void rotate(int x, int y, int L, int[][] tmp) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                tmp[j + x][y + L - i - 1] = A[i + x][j + y];
            }
        }

    }
}
