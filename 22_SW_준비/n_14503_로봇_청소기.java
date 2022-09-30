import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_14503_로봇_청소기 {
    static final int CLEAN = -2;
    static int N, M, x, y, dir, answer;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
        System.out.println(answer);
    }

    private static void solution() {
        boolean exit;
        while (true) {
            if (map[x][y] == 0) {
                map[x][y] = CLEAN;
                answer += 1;
            }

            exit = false;
            int nx, ny, backDir;
            for (int i = 0; i < 4; i++) {
                dir = dir - 1 == -1 ? 3 : dir - 1;

                nx = x + dx[dir];
                ny = y + dy[dir];

                if (map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    break;
                }

                if (i == 3) {
                    if(dir == 0) backDir = 2;
                    else if(dir == 1) backDir = 3;
                    else if(dir == 2) backDir = 0;
                    else backDir = 1;

                    nx = x + dx[backDir];
                    ny = y + dy[backDir];

                    if (map[nx][ny] != 1) {
                        x = nx;
                        y = ny;
                        break;
                    }

                    exit = true;
                }
            }
            if(exit) break;

        }

    }
}
