import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14499_주사위_굴리기 {
    static int N, M, x, y, K;
    static int[][] map;
    static int[] garo, sero;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        x = Integer.parseInt(stringTokenizer.nextToken());
        y = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        garo = new int[4];
        sero = new int[4];

        stringTokenizer = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            int d = Integer.parseInt(stringTokenizer.nextToken());

            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;

            if (d == 1) {
                int tmp = garo[3];
                for (int i = 3; i > 0; i--) {
                    garo[i] = garo[i - 1];
                }
                garo[0] = tmp;

                if (map[nx][ny] == 0) {
                    map[nx][ny] = garo[3];
                } else {
                    garo[3] = map[nx][ny];
                    map[nx][ny] = 0;
                }
                sero[1] = garo[1];
                sero[3] = garo[3];

            } else if (d == 2) {
                int tmp = garo[0];
                for (int i = 0; i < 3; i++) {
                    garo[i] = garo[i + 1];
                }
                garo[3] = tmp;

                if (map[nx][ny] == 0) {
                    map[nx][ny] = garo[3];
                } else {
                    garo[3] = map[nx][ny];
                    map[nx][ny] = 0;
                }
                sero[1] = garo[1];
                sero[3] = garo[3];
            } else if (d == 3) {
                int tmp = sero[0];
                for (int i = 0; i < 3; i++) {
                    sero[i] = sero[i + 1];
                }
                sero[3] = tmp;

                if (map[nx][ny] == 0) {
                    map[nx][ny] = sero[3];
                } else {
                    sero[3] = map[nx][ny];
                    map[nx][ny] = 0;
                }
                garo[1] = sero[1];
                garo[3] = sero[3];

            } else {
                int tmp = sero[3];
                for (int i = 3; i > 0; i--) {
                    sero[i] = sero[i - 1];
                }
                sero[0] = tmp;
                if (map[nx][ny] == 0) {
                    map[nx][ny] = sero[3];
                } else {
                    sero[3] = map[nx][ny];
                    map[nx][ny] = 0;
                }
                garo[1] = sero[1];
                garo[3] = sero[3];

            }

            System.out.println(garo[1]);
            x = nx;
            y = ny;
        }
    }
}
