import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14503_로봇_청소기 {
    static int N, M, x, y, d, ans = 0;
    static int[][] map;
    static boolean[][] isCleaned;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][M];
        isCleaned = new boolean[N][M];

        stringTokenizer = new StringTokenizer(br.readLine());
        x = Integer.parseInt(stringTokenizer.nextToken());
        y = Integer.parseInt(stringTokenizer.nextToken());
        d = Integer.parseInt(stringTokenizer.nextToken());

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        GoClean();

        System.out.println(ans);
    }

    private static void GoClean() {
        int dirCnt = 0, nx, ny;

        while (true) {
            if (!isCleaned[x][y] && map[x][y] == 0) {
                ans += 1;
                isCleaned[x][y] = true;
            }

            while (true) {
                if (dirCnt == 4) {
                    nx = x - dx[d];
                    ny = y - dy[d];

                    if(map[nx][ny] == 1) return;

                    x = nx;
                    y = ny;
                    dirCnt = 0;
                }

                d = d - 1 == -1 ? 3 : d - 1;
                nx = x + dx[d];
                ny = y + dy[d];

                if (!isCleaned[nx][ny] && map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    dirCnt = 0;
                    break;
                }
                dirCnt += 1;
            }
        }


    }
}
