import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14503_로봇_청소기_re {
    static int N, M, x, y, dir;
    static int[][] map;
    static boolean[][] isCleaned;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(br.readLine());
        x = Integer.parseInt(stringTokenizer.nextToken());
        y = Integer.parseInt(stringTokenizer.nextToken());
        dir = Integer.parseInt(stringTokenizer.nextToken());

        isCleaned = new boolean[N][M];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int dirCnt = 0, ans = 1;
        while (true) {
            isCleaned[x][y] = true;

            dirCnt += 1;
            dir = dir - 1 == -1 ? 3 : dir - 1;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
            if (!isCleaned[nx][ny] && map[nx][ny] == 0) {
                x = nx;
                y = ny;

                isCleaned[x][y] = true;
                ans += 1;
                dirCnt = 0;
            } else if (dirCnt == 4) {
                int countDir = getDir(dir);

                int backX = x + dx[countDir];
                int backY = y + dy[countDir];

                if (map[backX][backY] == 1) {
                    break;
                } else {
                    x = backX;
                    y = backY;
                    dirCnt = 0;
                }
            }
        }
        System.out.println(ans);
    }

    private static int getDir(int dir) {
        if(dir == 0) return 2;
        else if(dir == 1) return 3;
        else if(dir == 2) return 0;
        else return 1;
    }
}
