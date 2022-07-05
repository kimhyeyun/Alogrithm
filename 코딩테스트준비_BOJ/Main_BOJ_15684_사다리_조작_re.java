import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_15684_사다리_조작_re {
    static int N, M, H, ans;
    static int[][] ladder;
    static boolean isFinish;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        H = Integer.parseInt(stringTokenizer.nextToken());

        ladder = new int[H][N];
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int b = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            ladder[a][b] = 1;
            ladder[a][b + 1] = 2;
        }

        for (int cnt = 0; cnt <= 3; cnt++) {
            ans = cnt;
            DFS(0, 0);
            if (isFinish) {
                System.out.println(ans);
                return;
            }
        }
        System.out.println(-1);
    }

    private static void DFS(int x, int cnt) {
        if(isFinish) return;
        if (ans == cnt) {
            if(check()) isFinish = true;
            return;
        }

        for (int i = x; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
                    ladder[i][j] = 1;
                    ladder[i][j + 1] = 2;
                    DFS(i, cnt + 1);
                    ladder[i][j] = ladder[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean check() {
        for (int i = 0; i < N; i++) {
            int idx = i;
            for (int j = 0; j < H; j++) {
                if(ladder[j][idx] == 1) idx += 1;
                else if (ladder[j][idx] == 2) idx -= 1;
            }
            if(idx != i) return false;
        }
        return true;
    }
}
