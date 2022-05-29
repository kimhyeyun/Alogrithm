import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_14502_연구소_re {
    static int N, M, ans = Integer.MIN_VALUE;
    static int[][] map;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        setWall(0);
        System.out.println(ans);
    }

    private static void setWall(int cnt) {
        if (cnt == 3) {
            spreadVirus();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    setWall(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void spreadVirus() {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, tmp[i], 0, M);
        }

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tmp[i][j] == 2) q.add(new int[]{i, j});
            }
        }
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny || tmp[nx][ny] != 0) continue;

                tmp[nx][ny] = 1;
                q.add(new int[]{nx, ny});
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tmp[i][j] == 0) sum += 1;
            }
        }

        ans = ans < sum ? sum : ans;

    }
}
