import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_19237_어른_상어_re {
    static class Shark {
        int x, y, dir;

        public Shark(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int N, M, K;
    static int[][] remainingTimeOfSmell, sharkNumOfSmell;
    static int[][][] priorityOfDir;
    static Shark[] sharks;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        remainingTimeOfSmell = new int[N][N];
        sharkNumOfSmell = new int[N][N];
        priorityOfDir = new int[M + 1][4][4];
        sharks = new Shark[M + 1];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(stringTokenizer.nextToken());
                if (n > 0) {
                    sharks[n] = new Shark(i, j, 0);
                    remainingTimeOfSmell[i][j] = K;
                    sharkNumOfSmell[i][j] = n;
                }
            }
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].dir = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                stringTokenizer = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    priorityOfDir[i][j][k] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                }
            }
        }

        System.out.println(solve());

    }

    private static int solve() {
        int time = 0;

        while (true) {
            int cnt = 0;
            for (int i = 1; i <= M; i++) {
                if(sharks[i] != null) cnt += 1;
            }

            if(cnt == 1 && sharks[1] != null) return time;
            if(time >= 1000) return -1;

            int[][] tmp = new int[N][N];
            for (int shark = 1; shark <= M; shark++) {
                if(sharks[shark] != null) moveShark(tmp, shark);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(remainingTimeOfSmell[i][j] > 0) remainingTimeOfSmell[i][j] -= 1;
                    if(remainingTimeOfSmell[i][j] == 0) sharkNumOfSmell[i][j] = 0;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tmp[i][j] > 0) {
                        remainingTimeOfSmell[i][j] = K;
                        sharkNumOfSmell[i][j] = tmp[i][j];
                    }
                }
            }
            time += 1;
        }
    }

    private static void moveShark(int[][] tmp, int shark) {
        int d = 0, nx = 0, ny = 0;
        boolean flag = false;

        for (int i = 0; i < 4; i++) {
            d = priorityOfDir[shark][sharks[shark].dir][i];
            nx = sharks[shark].x + dx[d];
            ny = sharks[shark].y + dy[d];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
            if (sharkNumOfSmell[nx][ny] == 0) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            for (int i = 0; i < 4; i++) {
                d = priorityOfDir[shark][sharks[shark].dir][i];
                nx = sharks[shark].x + dx[d];
                ny = sharks[shark].y + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if (sharkNumOfSmell[nx][ny] == shark) break;
            }
        }

        if ((0 <= nx && nx < N) && (0 <= ny && ny < N) && tmp[nx][ny] == 0) {
            tmp[nx][ny] = shark;
            sharks[shark].x = nx;
            sharks[shark].y = ny;
            sharks[shark].dir = d;
        } else {
            sharks[shark] = null;
        }
    }
}
