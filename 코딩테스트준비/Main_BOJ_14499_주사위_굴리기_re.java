import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14499_주사위_굴리기_re {
    static int N, M, K, x, y;
    static int[] diceHorizon, diceVertical;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        x = Integer.parseInt(stringTokenizer.nextToken());
        y = Integer.parseInt(stringTokenizer.nextToken());

        K = Integer.parseInt(stringTokenizer.nextToken());

        diceHorizon = new int[4];
        diceVertical = new int[4];

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            int d = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;

            if (d == 0) {
                //동쪽
                int tmp = diceHorizon[3];
                for (int i = 3; i > 0; i--) {
                    diceHorizon[i] = diceHorizon[i - 1];
                }
                diceHorizon[0] = tmp;

                diceVertical[1] = diceHorizon[1];
                diceVertical[3] = diceHorizon[3];
            } else if (d == 1) {
                // 서
                int tmp = diceHorizon[0];
                for (int i = 0; i < 3; i++) {
                    diceHorizon[i] = diceHorizon[i + 1];
                }
                diceHorizon[3] = tmp;

                diceVertical[1] = diceHorizon[1];
                diceVertical[3] = diceHorizon[3];
            } else if (d == 2) {
                // 북
                int tmp = diceVertical[0];
                for (int i = 0; i < 3; i++) {
                    diceVertical[i] = diceVertical[i + 1];
                }
                diceVertical[3] = tmp;

                diceHorizon[1] = diceVertical[1];
                diceHorizon[3] = diceVertical[3];
            } else {
                // 남
                int tmp = diceVertical[3];
                for (int i = 3; i > 0; i--) {
                    diceVertical[i] = diceVertical[i - 1];
                }
                diceVertical[0] = tmp;

                diceHorizon[1] = diceVertical[1];
                diceHorizon[3] = diceVertical[3];
            }

            if (map[nx][ny] == 0) {
                map[nx][ny] = diceVertical[3];
            } else {
                diceVertical[3] = diceHorizon[3] = map[nx][ny];
                map[nx][ny] = 0;
            }

            x = nx;
            y = ny;

            sb.append(diceHorizon[1]).append("\n");
        }
        System.out.println(sb);
    }
}
