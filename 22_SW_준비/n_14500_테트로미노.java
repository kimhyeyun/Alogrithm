import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class n_14500_테트로미노 {
    static int N, M, answer;
    static int[][] board;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = Integer.MIN_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                isVisited[i][j] = true;
                solution(i, j, 1, board[i][j]);
                middlePuzzle(i, j);
                isVisited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    private static void middlePuzzle(int x, int y) {
        //ㅏ
        if(0 < x && x < N - 1 && 0 <= y && y < M - 1)
            answer = Math.max(answer, board[x][y] + board[x - 1][y] + board[x + 1][y] + board[x][y + 1]);
        //ㅓ
        if(0 < x && x < N - 1 && 0 < y && y < M)
            answer = Math.max(answer, board[x][y] + board[x - 1][y] + board[x + 1][y] + board[x][y - 1]);
        //ㅗ
        if(0 < x && x < N && 0 < y && y < M-1)
            answer = Math.max(answer, board[x][y] + board[x][y - 1] + board[x - 1][y] + board[x][y + 1]);
        //ㅜ
        if(0 <= x && x < N - 1 && 0 < y && y < M-1)
            answer = Math.max(answer, board[x][y] + board[x][y - 1] + board[x + 1][y] + board[x][y + 1]);
    }

    private static void solution(int x, int y, int cnt, int sum) {
        if (cnt == 4) {
            answer = answer < sum ? sum : answer;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
            if(isVisited[nx][ny]) continue;

            isVisited[nx][ny] = true;
            solution(nx, ny, cnt + 1, sum + board[nx][ny]);
            isVisited[nx][ny] = false;
        }
    }
}
