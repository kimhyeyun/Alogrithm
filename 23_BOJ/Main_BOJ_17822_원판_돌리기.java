import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17822_원판_돌리기 {
    static int N, M, T;
    static int[][] circles;
    static boolean flag;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        circles = new int[N + 1][M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                circles[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            flag = false;

            rotateCircle(n, d, k);
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (circles[i][j] == 0) continue;
                    isAdjNumber(i, j, circles[i][j]);
                }
            }
            if (!flag) calAvg();
        }
        System.out.println(printSum());

    }

    private static void print() {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(circles[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int printSum() {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                sum += circles[i][j];
            }
        }
        return sum;
    }

    private static void calAvg() {
        double avg = 0, count = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(circles[i][j] == 0) continue;
                avg += circles[i][j];
                count += 1.0;
            }
        }
        avg = avg / count;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(circles[i][j] == 0 || (double) circles[i][j] == avg) continue;
                if(circles[i][j] < avg) circles[i][j] += 1;
                else circles[i][j] -= 1;
            }
        }
    }

    private static void isAdjNumber(int x, int y, int number) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(N < nx || nx <= 0) continue;
                if(ny >= M) ny = 0;
                else if(ny == -1) ny = M - 1;
                if(circles[nx][ny] != number) continue;

                q.add(new int[]{nx, ny});
                circles[nx][ny] = 0;
                flag = true;
            }
        }
    }

    private static void rotateCircle(int number, int dir, int count) {
        if (dir == 0) {
            for(int index = number ; index <= N ;index += number) {
                int[] tmp = new int[M];
                for (int i = 0; i < M; i++) {
                    tmp[(i + count) % M] = circles[index][i];
                }
                circles[index] = tmp.clone();
            }
        } else {
            for(int index = number ; index <= N; index += number) {
                int[] tmp = new int[M];
                for (int i = 0; i < M; i++) {
                    tmp[(i - count + M) % M] = circles[index][i];
                }
                circles[index] = tmp.clone();
            }
        }
    }
}
