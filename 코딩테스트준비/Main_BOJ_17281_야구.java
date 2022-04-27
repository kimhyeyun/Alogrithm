import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17281_야구 {
    static int N, ans;
    static int[][] playerResult;
    static int[] selPlayer;
    static boolean[] isSel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());

        playerResult = new int[N][10];
        selPlayer = new int[10];
        isSel = new boolean[10];
        ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                playerResult[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        selPlayer[4] = 1;
        isSel[4] = true;

        DFS(2);

        System.out.println(ans);
    }

    private static void DFS(int playerNum) {
        if (playerNum == 10) {
            playGame();
            return;
        }

        for (int i = 1; i < 10; i++) {
            if(isSel[i]) continue;

            selPlayer[i] = playerNum;
            isSel[i] = true;
            DFS(playerNum + 1);
            isSel[i] = false;
        }
    }

    private static void playGame() {
        int score = 0, player = 1;

        for (int i = 0; i < N; i++) {
            int out = 0;
            boolean[] base = new boolean[4];

            outer:while (true) {
                for (int p = player; p < 10; p++) {
                    int hit = playerResult[i][selPlayer[p]];

                    switch (hit) {
                        case 0:
                            out += 1;
                            break;
                        case 1:
                            for (int j = 3; j > 0; j--) {
                                if (base[j]) {
                                    if (j == 3) {
                                        score += 1;
                                        base[j] = false;
                                    } else {
                                        base[j] = false;
                                        base[j + 1] = true;
                                    }
                                }
                            }
                            base[1] = true;
                            break;
                        case 2:
                            for (int j = 3; j > 0; j--) {
                                if (base[j]) {
                                    if (j == 2 || j == 3) {
                                        score += 1;
                                        base[j] = false;
                                    } else {
                                        base[j] = false;
                                        base[j + 2] = true;
                                    }
                                }
                            }
                            base[2] = true;
                            break;
                        case 3:
                            for (int j = 3; j > 0; j--) {
                                if (base[j]) {
                                    score += 1;
                                    base[j] = false;
                                }
                            }
                            base[3] = true;
                            break;
                        case 4:
                            for (int j = 1; j < 4; j++) {
                                if (base[j]) {
                                    score += 1;
                                    base[j] = false;
                                }
                            }
                            score += 1;
                            break;
                    }

                    if (out == 3) {
                        player = p + 1;
                        if (player == 10) {
                            player = 1;
                        }
                        break outer;
                    }
                }

                player = 1;
            }
        }
        ans = Math.max(ans, score);
    }
}
