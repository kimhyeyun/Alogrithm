import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n_17822_원판_돌리기 {
    static int N, M, T;
    static int[][] circles;
    static boolean isChange, isSame;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        circles = new int[N + 1][M];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                circles[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            rotateCircles(x, d, k);
            isChange = false;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    isSame = false;
                    if(circles[i][j] == -1) continue;
                    checkAdjNumber(i, j, circles[i][j]);
                    if(isSame) circles[i][j] = -1;
                }
            }
            if(!isChange) changeNumbers();
        }

        System.out.println(printSum());
    }

    private static int printSum() {
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(circles[i][j] == -1) continue;
                answer += circles[i][j];
            }
        }
        return answer;
    }

    private static void changeNumbers() {
        int sum = 0;
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(circles[i][j] == -1) continue;
                sum += circles[i][j];
                cnt += 1;
            }
        }

        double avg = (double) sum / cnt;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if(circles[i][j] == -1) continue;
                if((double)circles[i][j] > avg) circles[i][j] -= 1;
                else if((double)circles[i][j] < avg) circles[i][j] += 1;
            }
        }
    }

    private static void checkAdjNumber(int x, int y, int num) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] isChecked = new boolean[N + 1][M];
        q.add(new int[]{x, y});
        isChecked[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(ny == M) ny = 0;
                if(ny == -1) ny = M - 1;

                if(nx < 1 || ny < 0 || N < nx || M <= ny) continue;
                if(isChecked[nx][ny] || circles[nx][ny] != num) continue;

                isSame = true;
                isChange = true;
                circles[nx][ny] = -1;
                q.add(new int[]{nx, ny});
                isChecked[nx][ny] = true;
            }
        }
    }

    private static void rotateCircles(int x, int d, int k) {
        for (int i = x; i < N + 1; i += x) {
            if (d == 0) {
                for (int j = 0; j < k; j++) {
                    int tmp = circles[i][M - 1];
                    for (int z = M - 1; z > 0; z--) {
                        circles[i][z] = circles[i][z - 1];
                    }
                    circles[i][0] = tmp;
                }
            } else {
                for (int j = 0; j < k; j++) {
                    int tmp = circles[i][0];
                    for (int z = 0; z < M - 1; z++) {
                        circles[i][z] = circles[i][z + 1];
                    }
                    circles[i][M - 1] = tmp;
                }
            }
        }
    }
}
