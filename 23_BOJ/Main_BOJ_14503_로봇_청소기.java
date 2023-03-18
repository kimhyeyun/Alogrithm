import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14503_로봇_청소기 {
    static int N, M, x, y, d;
    static int[][] room;
    static final int NON_CLEAN = 0, WALL = 1, CLEAN = 2;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        room = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(cleaning());
    }

    private static int cleaning() {
        int cntOfClean = 0;

        while (true) {

            if (room[x][y] == NON_CLEAN) {
                room[x][y] = CLEAN;
                cntOfClean += 1;
            }

            int nx, ny;
            int cntOfRotate = 0;
            while (true) {
                if (cntOfRotate == 4) {
                    nx = x - dx[d];
                    ny = y - dy[d];

                    if(room[nx][ny] == WALL) return cntOfClean;

                    x = nx;
                    y = ny;
                    break;
                }

                d = d - 1 == -1 ? 3 : d - 1;
                nx = x + dx[d];
                ny = y + dy[d];

                if (room[nx][ny] == NON_CLEAN) {
                    x = nx;
                    y = ny;
                    cntOfRotate = 0;
                    break;
                } else cntOfRotate += 1;
            }
        }
    }
}
