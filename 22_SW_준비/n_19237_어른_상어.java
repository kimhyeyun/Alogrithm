import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_19237_어른_상어 {
    static class Shark {
        int x, y, dir;

        public Shark(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int N, M, k;
    static int[][] remainingTimeOfSmell, numberOfSmell;
    static int[][][] priorityOfDir;
    static Shark[] sharks;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        remainingTimeOfSmell = new int[N][N];
        numberOfSmell = new int[N][N];
        priorityOfDir = new int[M + 1][5][4];
        sharks = new Shark[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n > 0) {
                    sharks[n] = new Shark(i, j, 0);
                    remainingTimeOfSmell[i][j] = k;
                    numberOfSmell[i][j] = n;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            int dir = Integer.parseInt(st.nextToken());
            sharks[i].dir = dir;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    priorityOfDir[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.out.println(solution());
    }

    private static int solution() {
        int time = 0;

        while (true) {
            int numberOfSharks = 0;
            for (int i = 1; i <= M; i++) {
                if(sharks[i] != null) numberOfSharks += 1;
            }

            if(numberOfSharks == 1 && sharks[1] != null) return time;
            if(time >= 1000) return -1;

            int[][] tmp = new int[N][N];
            for (int i = 1; i <= M; i++) {
                if(sharks[i] == null) continue;
                moveShark(i, tmp);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(remainingTimeOfSmell[i][j] > 0) remainingTimeOfSmell[i][j] -= 1;
                    if(remainingTimeOfSmell[i][j] == 0) numberOfSmell[i][j] = 0;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tmp[i][j] > 0) {
                        numberOfSmell[i][j] = tmp[i][j];
                        remainingTimeOfSmell[i][j] = k;
                    }
                }
            }
            time += 1;
        }
    }

    private static void moveShark(int num, int[][] tmp) {
        int priority = 0, nx = 0, ny = 0;
        boolean flag = false;

        for (int d = 0; d < 4; d++) {
            priority = priorityOfDir[num][sharks[num].dir][d];
            nx = sharks[num].x + dx[priority];
            ny = sharks[num].y + dy[priority];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
            if (numberOfSmell[nx][ny] == 0) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            for (int d = 0; d < 4; d++) {
                priority = priorityOfDir[num][sharks[num].dir][d];
                nx = sharks[num].x + dx[priority];
                ny = sharks[num].y + dy[priority];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if (numberOfSmell[nx][ny] == num) break;
            }
        }

        if ((0 <= nx && nx < N) && (0 <= ny && ny < N) && tmp[nx][ny] == 0) {
            tmp[nx][ny] = num;
            sharks[num].x = nx;
            sharks[num].y = ny;
            sharks[num].dir = priority;
        } else {
            sharks[num] = null;
        }
    }
}
