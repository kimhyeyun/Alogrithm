import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14500_테트로미노 {
    static int N, M, ans = 0;
    static int[][] map;
    static boolean[][] isVisited;
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

        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                isVisited[i][j] = true;
                DFS(i, j, 1, map[i][j]);
                middle(i, j);
                isVisited[i][j] = false;
            }
        }
        System.out.println(ans);

    }

    private static void middle(int x, int y) {
        // ㅏ
        if(0 < x && x < N-1 && y < M-1)
            ans = Math.max(ans, map[x][y] + map[x - 1][y] + map[x + 1][y] + map[x][y + 1]);
        if(0 < x && x < N-1 && 0 < y)
            // ㅓ
            ans = Math.max(ans, map[x][y] + map[x - 1][y] + map[x + 1][y] + map[x][y - 1]);
        if(0 < x && 0 < y && y < M-1)
            // ㅗ
            ans = Math.max(ans, map[x][y] + map[x - 1][y] + map[x][y - 1] + map[x][y + 1]);
        if(x < N-1 && 0 < y && y < M-1)
            // ㅜ
            ans = Math.max(ans, map[x][y] + map[x][y - 1] + map[x][y + 1] + map[x + 1][y]);

    }

    private static void DFS(int x, int y, int cnt, int sum) {
        if (cnt >= 4) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny || isVisited[nx][ny]) continue;

            isVisited[nx][ny] = true;
            DFS(nx, ny, cnt + 1, sum + map[nx][ny]);
            isVisited[nx][ny] = false;
        }
    }
}
