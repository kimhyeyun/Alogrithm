import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_15684_사다리_조작 {
    static int N, M, H, answer;
    static int[][] ladder;
    static boolean isFinished;
    static final int RIGHT = 1, LEFT = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new int[H][N];

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            ladder[a][b] = RIGHT;
            ladder[a][b + 1] = LEFT;
        }

        answer = 0;
        isFinished = false;

        for (int cnt = 0; cnt <= 3; cnt++) {
            answer = cnt;
            DFS(0, 0);

            if (isFinished) {
                System.out.println(cnt);
                return;
            }
        }
        System.out.println(-1);
    }

    private static void DFS(int count, int x) {
        if(isFinished) return;
        if (count == answer) {
            if(check()) isFinished = true;
            return;
        }

        for (int i = x; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if(ladder[i][j] != 0 || ladder[i][j+1] != 0) continue;

                ladder[i][j] = RIGHT;
                ladder[i][j + 1] = LEFT;
                DFS(count + 1, i);
                ladder[i][j] = ladder[i][j + 1] = 0;
            }
        }
    }

    private static boolean check() {
        for (int i = 0; i < N; i++) {
            int idx = i;
            for (int j = 0; j < H; j++) {
                if(ladder[j][idx] == RIGHT) idx += 1;
                else if(ladder[j][idx] == LEFT) idx -= 1;
            }
            if(idx != i) return false;
        }
        return true;
    }
}
