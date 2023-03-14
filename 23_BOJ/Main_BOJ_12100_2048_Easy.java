import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_12100_2048_Easy {
    static int N, answer;
    static int[][] board;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        answer = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0);

        System.out.println(answer);
    }

    private static void DFS(int cnt) {
        if (cnt == 5) {
            answer = Math.max(answer, findMax());
            return;
        }

        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, tmp[i], 0, N);
        }

        for (int d = 0; d < 4; d++) {
            move(d);
            DFS(cnt + 1);

            for (int i = 0; i < N; i++) {
                System.arraycopy(tmp[i], 0, board[i], 0, N);
            }
        }
    }

    private static int findMax() {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
        return max;
    }

    private static void move(int dir) {
        isVisited = new boolean[N][N];

        if (dir == 0) {
            // 위
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    moveBlock(i, j, dir);
                }
            }
        } else if (dir == 1) {
            // 왼
            for (int j = 0; j < N; j++) {
                for (int i = 0; i < N; i++) {
                    moveBlock(i, j, dir);
                }
            }
        } else if (dir == 2) {
            // 오
            for (int j = N - 1; j >= 0; j--) {
                for (int i = 0; i < N; i++) {
                    moveBlock(i, j, dir);
                }
            }
        } else {
            // 밑
            for (int i = N - 1; i >= 0; i--) {
                for (int j = 0; j < N; j++) {
                    moveBlock(i, j, dir);
                }
            }
        }
    }

    private static void moveBlock(int x, int y, int dir) {
        if(board[x][y] == 0) return;

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny || isVisited[nx][ny]) return;

            if (board[x][y] == board[nx][ny]) {
                isVisited[nx][ny] = true;
                board[nx][ny] *= 2;
                board[x][y] = 0;
                return;
            }else if(board[nx][ny] != 0) return;

            board[nx][ny] = board[x][y];
            board[x][y] = 0;
            x = nx;
            y = ny;
        }
    }
}
