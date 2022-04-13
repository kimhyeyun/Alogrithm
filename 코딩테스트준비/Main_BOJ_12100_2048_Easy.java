import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_12100_2048_Easy {
    static int N, ans = 0;
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

        int[][] copy = new int[N][N];
        for (int j = 0; j < N; j++) {
            copy[j] = board[j].clone();
        }

        for (int i = 0; i < 4; i++) {
            move(i);
            DFS(cnt + 1);

            for (int j = 0; j < N; j++) {
                board[j] = copy[j].clone();
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

    private static void move(int dir) {
        switch (dir) {
            case 0:
                for (int i = 0; i < N; i++) {
                    int block = 0, idx = 0;

                    for (int j = 0; j < N; j++) {
                        if(board[j][i] == 0) continue;

                        if (block == board[j][i]) {
                            board[idx - 1][i] = block * 2;
                            block = 0;
                            board[j][i] = 0;
                        } else {
                            block = board[j][i];
                            board[j][i] = 0;
                            board[idx][i] = block;
                            idx += 1;
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < N; i++) {
                    int idx = N - 1, block = 0;

                    for (int j = N - 1; j >= 0; j--) {
                        if(board[j][i] == 0) continue;
                        if (block == board[j][i]) {
                            board[idx + 1][i] = block * 2;
                            block = 0;
                            board[j][i] = 0;
                        } else {
                            block = board[j][i];
                            board[j][i] = 0;
                            board[idx][i] = block;
                            idx -= 1;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < N; i++) {
                    int idx = 0, block = 0;
                    for (int j = 0; j < N; j++) {
                        if(board[i][j] == 0) continue;
                        if (board[i][j] == block) {
                            board[i][idx - 1] = block * 2;
                            block = 0;
                            board[i][j] = 0;
                        } else {
                            block = board[i][j];
                            board[i][j] = 0;
                            board[i][idx] = block;
                            idx += 1;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < N; i++) {
                    int idx = N - 1, block = 0;
                    for (int j = N - 1; j >= 0; j--) {
                        if(board[i][j] == 0) continue;
                        if (block == board[i][j]) {
                            board[i][idx + 1] = block * 2;
                            block = 0;
                            board[i][j] = 0;
                        } else {
                            block = board[i][j];
                            board[i][j] = 0;
                            board[i][idx] = block;
                            idx -= 1;
                        }
                    }
                }
                break;
        }
    }
}
