import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_12100_2048_Easy_re {
    static int N, ans = Integer.MIN_VALUE;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        DFS(0);

        System.out.println(ans);
    }

    private static void DFS(int cnt) {
        if (cnt == 5) {
            findMax();
            return;
        }

        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, tmp[i], 0, N);
        }

        for (int d = 0; d < 4; d++) {
            moveBlock(d);
            DFS(cnt + 1);

            for (int i = 0; i < N; i++) {
                System.arraycopy(tmp[i], 0, board[i], 0, N);
            }
        }
    }

    private static void findMax() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = ans < board[i][j] ? board[i][j] : ans;
            }
        }
    }

    private static void moveBlock(int dir) {
        switch (dir) {
            case 0:
                // 위
                for (int y = 0; y < N; y++) {
                    int block = 0, idx = 0;
                    for (int x = 0; x < N; x++) {
                        if(board[x][y] == 0) continue;

                        if (block == board[x][y]) {
                            board[idx - 1][y] = block * 2;
                            board[x][y] = 0;
                            block = 0;
                        } else {
                            block = board[x][y];
                            board[x][y] = 0;
                            board[idx][y] = block;
                            idx += 1;
                        }
                    }
                }
                break;

            case 1:
                //왼
                for (int x = 0; x < N; x++) {
                    int idx = 0, block = 0;
                    for (int y = 0; y < N; y++) {
                        if(board[x][y] == 0) continue;

                        if (block == board[x][y]) {
                            board[x][idx - 1] = block * 2;
                            board[x][y] = 0;
                            block = 0;
                        } else {
                            block = board[x][y];
                            board[x][y] = 0;
                            board[x][idx] = block;
                            idx += 1;
                        }
                    }
                }
                break;

            case 2:
                // 오
                for (int x = 0; x < N; x++) {
                    int idx = N - 1, block = 0;
                    for (int y = N - 1; y >= 0; y--) {
                        if(board[x][y] == 0) continue;

                        if (block == board[x][y]) {
                            board[x][idx + 1] = block * 2;
                            board[x][y] = 0;
                            block = 0;
                        } else {
                            block = board[x][y];
                            board[x][y] = 0;
                            board[x][idx] = block;
                            idx -= 1;
                        }
                    }
                }
                break;

            case 3:
                // 아래
                for (int y = 0; y < N; y++) {
                    int idx = N - 1, block = 0;
                    for (int x = N - 1; x >= 0; x--) {
                        if(board[x][y] == 0) continue;

                        if (block == board[x][y]) {
                            board[idx + 1][y] = block * 2;
                            board[x][y] = 0;
                            block = 0;
                        } else {
                            block = board[x][y];
                            board[x][y] = 0;
                            board[idx][y] = block;
                            idx -= 1;
                        }
                    }
                }
                break;
        }
    }
}
