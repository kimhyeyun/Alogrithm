import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_19237_어른_상어 {

    static class Shark{
        int x;
        int y;
        int dir;

        public Shark(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static int N, M, k;
    static int[][] remainingTimeOfSmell, sharkNumOfSmell;
    static int[][][] priorityOfDir;
    static Shark[] sharks;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());

        remainingTimeOfSmell = new int[N][N];
        sharkNumOfSmell = new int[N][N];
        priorityOfDir = new int[M + 1][5][4];
        sharks = new Shark[M + 1];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(stringTokenizer.nextToken());
                if (tmp > 0) {
                    sharks[tmp] = new Shark(i, j, 0);
                    remainingTimeOfSmell[i][j] = k;
                    sharkNumOfSmell[i][j] = tmp;
                }
            }
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].dir = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= 4; j++) {
                stringTokenizer = new StringTokenizer(br.readLine());
                for (int l = 0; l < 4; l++) {
                    priorityOfDir[i][j][l] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
        }

        System.out.println(Solve());
    }

    private static int Solve() {
        int time = 0;

        while (true) {
            int cnt = 0;
            for (int i = 1; i <= M; i++) {
                if (sharks[i] != null) {
                    cnt += 1;
                }
            }

            if(cnt == 1 && sharks[1] != null) return time;
            if(time >= 1000) return -1;

            int[][] tmp = new int[N][N];
            for (int shark = 1; shark <= M; shark++) {
                if(sharks[shark] != null) moveShark(tmp, shark);
            }

            // 냄새 유효기간 하나씩 줄이기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(remainingTimeOfSmell[i][j] > 0) remainingTimeOfSmell[i][j] -= 1;

                    if(remainingTimeOfSmell[i][j] == 0) sharkNumOfSmell[i][j] = 0;
                }
            }

            // 이동 후의 상어 위치의 냄새 정보와 유효기간 초기화하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tmp[i][j] > 0) {
                        remainingTimeOfSmell[i][j] = k;
                        sharkNumOfSmell[i][j] = tmp[i][j];
                    }
                }
            }

            time += 1;
        }
    }

    private static void moveShark(int[][] tmp, int shark) {
        int d = 0;
        int nx = 0;
        int ny = 0;
        boolean flag = false;

        // 1-1. 높은 우선순위부터 차례대로 탐색
        for (int i = 0; i < 4; i++) {
            d = priorityOfDir[shark][sharks[shark].dir][i];
            nx = sharks[shark].x + dx[d];
            ny = sharks[shark].y + dy[d];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
            if ((0 <= nx && nx < N) && (0 <= ny && ny < N) && sharkNumOfSmell[nx][ny] == 0) {
                flag = true;
                break;
            }
        }

        // 1-2. 냄새가 없는 곳이 없는 경우
        if (!flag) {
            for (int i = 0; i < 4; i++) {
                d = priorityOfDir[shark][sharks[shark].dir][i];
                nx = sharks[shark].x + dx[d];
                ny = sharks[shark].y + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if ((0 <= nx && nx < N) && (0 <= ny && ny < N) && sharkNumOfSmell[nx][ny] == shark) break;
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

