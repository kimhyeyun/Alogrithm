import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14500_테트로미노 {
    static int N, M, answer;
    static int[][] paper;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MIN_VALUE;
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                isVisited[i][j] = true;
                checkPuzzle(i, j, 1, paper[i][j]);
                middlePuzzle(i, j);
                isVisited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    private static void middlePuzzle(int x, int y) {
        // ㅏ
        if (0 < x && x < N - 1 && 0 <= y && y < M - 1) {
            answer = Math.max(answer, paper[x][y] + paper[x - 1][y] + paper[x + 1][y] + paper[x][y + 1]);
        }

        // ㅓ
        if (0 < x && x < N - 1 && 0 < y && y < M) {
            answer = Math.max(answer, paper[x][y] + paper[x][y - 1] + paper[x - 1][y] + paper[x + 1][y]);
        }

        // ㅗ
        if (0 < x && x < N && 0 < y && y < M - 1) {
            answer = Math.max(answer, paper[x][y] + paper[x][y - 1] + paper[x - 1][y] + paper[x][y + 1]);
        }

        // ㅜ
        if (0 <= x && x < N - 1 && 0 < y && y < M - 1) {
            answer = Math.max(answer, paper[x][y] + paper[x][y - 1] + paper[x + 1][y] + paper[x][y + 1]);
        }
    }

    private static void checkPuzzle(int x, int y, int cnt, int sum) {
        if (cnt == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny || isVisited[nx][ny]) continue;

            isVisited[nx][ny] = true;
            checkPuzzle(nx, ny, cnt + 1, sum + paper[nx][ny]);
            isVisited[nx][ny] = false;
        }
    }
}
