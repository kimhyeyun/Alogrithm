import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_19237_어른_상어 {
    static class Shark {
        int x, y;
        int dir;

        public Shark(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int N, M, K;
    static int[][] indexOfSmell, remainingTimeOfSmell;
    static int[][][] priorityOfDirOfSharks;
    static Shark[] sharks;

    static final int[] dx = {0, -1, 1, 0, 0};
    static final int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        indexOfSmell = new int[N][N];
        remainingTimeOfSmell = new int[N][N];
        priorityOfDirOfSharks = new int[M + 1][5][4];
        sharks = new Shark[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                indexOfSmell[i][j] = Integer.parseInt(st.nextToken());
                if (indexOfSmell[i][j] > 0) {
                    sharks[indexOfSmell[i][j]] = new Shark(i, j, 0);
                    remainingTimeOfSmell[i][j] = K;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= 4; j++) {
                st = new StringTokenizer(br.readLine());

                for (int k = 0; k < 4; k++) {
                    priorityOfDirOfSharks[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.out.println(sol());
    }

    private static int sol() {
        int time = -1;

        while (time++ < 1000) {
            int countOfShark = 0;

            for (int i = 1; i <= M; i++) {
                if(sharks[i] != null) countOfShark += 1;
            }

            if(countOfShark == 1 && sharks[1] != null) return time;

            int[][] tmp = new int[N][N];
            for (int i = 1; i <= M; i++) {
                if(sharks[i] == null) continue;
                moveShark(i, tmp);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(remainingTimeOfSmell[i][j] > 0) remainingTimeOfSmell[i][j] -= 1;
                    if(remainingTimeOfSmell[i][j] == 0) indexOfSmell[i][j] = 0;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(tmp[i][j] == 0) continue;

                    indexOfSmell[i][j] = tmp[i][j];
                    remainingTimeOfSmell[i][j] = K;
                }
            }
        }
        return -1;
    }

    private static void moveShark(int index, int[][] tmp) {
        int priority = 0, nx = 0, ny = 0;
        boolean flag = false;

        for (int d = 0; d < 4; d++) {
            priority = priorityOfDirOfSharks[index][sharks[index].dir][d];
            nx = sharks[index].x + dx[priority];
            ny = sharks[index].y + dy[priority];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
            if (indexOfSmell[nx][ny] == 0) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            for (int d = 0; d < 4; d++) {
                priority = priorityOfDirOfSharks[index][sharks[index].dir][d];
                nx = sharks[index].x + dx[priority];
                ny = sharks[index].y + dy[priority];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if (indexOfSmell[nx][ny] == index) break;
            }
        }

        if ((0 <= nx && nx < N) && (0 <= ny && ny < N) && tmp[nx][ny] == 0) {
            tmp[nx][ny] = index;
            sharks[index].x = nx;
            sharks[index].y = ny;
            sharks[index].dir = priority;
        } else sharks[index] = null;
    }
}
