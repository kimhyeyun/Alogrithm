import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1018_체스판_다시_칠하기 {
    static int N, M, ans = 64;
    static boolean WHITE = true, BLACK = false;
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        board = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if(str.charAt(j) == 'W') board[i][j] = WHITE;
                else board[i][j] = BLACK;
            }
        }

        int nRow = N - 7;
        int nCol = M - 7;

        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                find(i, j);
            }
        }
        System.out.println(ans);
    }

    private static void find(int x, int y) {
        int endX = x + 8, endY = y + 8;
        int cnt = 0;

        boolean color = board[x][y];

        for (int i = x; i < endX; i++) {
            for (int j = y; j < endY; j++) {
                if(board[i][j] != color) cnt += 1;

                color = !color;
            }
            color = !color;
        }

        cnt = Math.min(cnt, 64 - cnt);

        ans = Math.min(ans, cnt);
    }
}
