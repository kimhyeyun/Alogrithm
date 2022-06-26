import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_17822_원판_돌리기_re {
    static final int RIGHT = 0, LEFT = 1, EMPTY = -1;
    static int N, M, T;
    static boolean isChange, isSame;
    static double[][] circles;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        T = Integer.parseInt(stringTokenizer.nextToken());

        circles = new double[N + 1][M];
        for (int i = 1; i < N + 1; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                circles[i][j] = Double.parseDouble(stringTokenizer.nextToken());
            }
        }

        while (T-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());

            rotateCircle(x, d, k);

            isChange = false;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    isSame = false;
                    if (circles[i][j] != EMPTY) {
                        checkAdjNum(i, j, circles[i][j]);

                        if(isSame) circles[i][j] = EMPTY;
                    }
                }
            }

            if(!isChange) calAverage();

//            printCircle();
        }

        System.out.println(sumOfCircles());

    }

    private static int sumOfCircles() {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(circles[i][j] == EMPTY) continue;
                sum += circles[i][j];
            }
        }

        return sum;
    }

    private static void calAverage() {
        double avg = 0, cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(circles[i][j] == EMPTY) continue;

                avg += circles[i][j];
                cnt += 1;
            }
        }

        avg /= cnt;
        changeNum(avg);
    }

    private static void changeNum(double avg) {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(circles[i][j] == EMPTY) continue;

                if(circles[i][j] < avg) circles[i][j] += 1;
                else if(circles[i][j] > avg) circles[i][j] -= 1;

            }
        }
    }

    private static void checkAdjNum(int x, int y, double num) {
        isVisited = new boolean[N + 1][M];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        isVisited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(ny == M) ny = 0;
                if(ny == -1) ny = M - 1;

                if(nx <= 0 || N < nx || ny < 0 || M <= ny || circles[nx][ny] != num) continue;

                q.add(new int[]{nx, ny});
                isVisited[nx][ny] = true;
                circles[nx][ny] = EMPTY;
                isSame = true;
                isChange = true;
            }
        }
    }

    private static void rotateCircle(int x, int d, int k) {
        for (int i = x; i < N + 1; i += x) {
            for (int a = 0; a < k; a++) {
                if (d == RIGHT) {
                    double tmp = circles[i][M - 1];
                    for (int j = M - 1; j > 0; j--) {
                        circles[i][j] = circles[i][j - 1];
                    }
                    circles[i][0] = tmp;
                } else {
                    double tmp = circles[i][0];
                    for (int j = 0; j < M - 1; j++) {
                        circles[i][j] = circles[i][j + 1];
                    }
                    circles[i][M - 1] = tmp;
                }
            }
        }
    }

    private static void printCircle() {
        for (int i = 1; i < N+1; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(circles[i][j] + " ");
            }
            System.out.println();
        }
    }
}
