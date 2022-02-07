import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17822_원판_돌리기 {

    static int N, M, T, x, d, k;
    static int[][] disc;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static boolean isSame, isChange = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        T = Integer.parseInt(stringTokenizer.nextToken());

        disc = new int[N + 1][M];
        for (int i = 1; i < N + 1; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                disc[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        while (T-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            x = Integer.parseInt(stringTokenizer.nextToken());
            d = Integer.parseInt(stringTokenizer.nextToken());
            k = Integer.parseInt(stringTokenizer.nextToken());

            rotateDisc(x, d, k);
//            System.out.println("x : " +x +" d: " + d +" k: " +k );
//            tostring();
            isChange = false;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    isSame = false;
                    if(disc[i][j] != 0) {
                        checkAdjNum(i, j, disc[i][j]);
                        if(isSame) {
                            disc[i][j] = 0;
/*                            System.out.println("i : "+i+" j : "+j );
                            tostring();*/
                        }
                    }
                }
            }

            if (!isChange) {
                calAverage();
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                ans += disc[i][j];
            }
        }
        System.out.println(ans);
    }

    private static void tostring() {
        for (int[] di : disc) {
            for (int d : di) {
                System.out.print(d + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void calAverage() {
//        System.out.println("calAverage()");
        float sum = 0;
        float cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                sum += disc[i][j];
                if(disc[i][j] != 0) cnt += 1;
            }
        }

        sum /= cnt;
        changeNum(sum);
    }

    private static void changeNum(float sum) {
//        System.out.println("sum: " + sum);
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(disc[i][j] != 0){
                    if(disc[i][j] < sum) disc[i][j] += 1;
                    else if(disc[i][j] > sum) disc[i][j] -= 1;
                }
            }
        }
//        tostring();
    }

    private static void checkAdjNum(int x, int y, int value) {
        isVisited = new boolean[N + 1][M];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        isVisited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(ny == M) ny = 0;
                if(ny == -1) ny = M - 1;

                if(nx <= 0 || N < nx || isVisited[nx][ny] || disc[nx][ny] != value) continue;

                q.add(new int[]{nx, ny});
                isVisited[nx][ny] = true;
                disc[nx][ny] = 0;
                isSame = true;
                isChange = true;
            }
        }

    }

    private static void rotateDisc(int x, int d, int k) {
        // 회전
        int dir = d == 0 ? 1 : -1;
        for (int i = x; i < N + 1; i += x) {
            int[] tmp = new int[M];
            for (int j = 0; j < M; j++) {
                tmp[(j + (dir * (k % M)) + M) % M] = disc[i][j];

            }
            disc[i] = Arrays.copyOf(tmp, M);
        }
    }
}
