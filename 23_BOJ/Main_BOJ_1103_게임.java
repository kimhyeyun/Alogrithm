import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1103_게임 {
    static int N, M, answer;
    static boolean isCycle;
    static char[][] arr;
    static boolean[][] isVisited;
    static int[][] dp;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = Integer.MIN_VALUE;
        isCycle = false;

        arr = new char[N][M];
        isVisited = new boolean[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        isVisited[0][0] = true;

        DFS(0, 0, 1);

        if(isCycle) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void DFS(int x, int y, int count) {
        if(count > answer) answer = count;

        dp[x][y] = count;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d] * (arr[x][y] - '0');
            int ny = y + dy[d] * (arr[x][y] - '0');

            if(nx < 0 || ny < 0 || N <= nx || M <= ny || arr[nx][ny] == 'H') continue;

            if (isVisited[nx][ny]) {
                isCycle = true;
                return;
            }

            if(dp[nx][ny] > count) continue;

            isVisited[nx][ny] = true;
            DFS(nx, ny, count + 1);
            isVisited[nx][ny] = false;
        }
    }
}
