import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_14503_로봇청소기 {

    static int N, M, r, c, d;
    static int cnt = 0;
    static int[][] map;
    static boolean[][] isCleaned;
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(br.readLine());
        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        d = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][M];
        isCleaned = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        Cleaning();
        System.out.println(cnt);

    }

    private static void Cleaning() {
        int dirCnt = 0, nc, nr;

        while (true) {
            if (map[r][c] == 0 && !isCleaned[r][c]) {
                isCleaned[r][c] = true;
                cnt++;
            }

            while (true) {
                if (dirCnt == 4) {
                    // 후진
                    nr = r - dr[d];
                    nc = c - dc[d];

                    if (map[nr][nc] == 1) return;
                    else {
                        r = nr;
                        c = nc;
                        dirCnt = 0;
                    }
                }

                d = d - 1;
                d = d == -1 ? 3 : d;
                nc = c + dc[d];
                nr = r + dr[d];

                if (map[nr][nc] == 0 && !isCleaned[nr][nc]) {
                    dirCnt = 0;
                    c = nc;
                    r = nr;
                    break;
                } else
                    dirCnt++;
            }
        }
    }
}
