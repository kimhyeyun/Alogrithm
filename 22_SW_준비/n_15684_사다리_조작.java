import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_15684_사다리_조작 {
    static int N, M, H, answer;
    static boolean isFinished;
    static int[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new int[H][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            ladder[a][b] = 1;
            ladder[a][b + 1] = 2;
        }

        answer = 0;
        isFinished = false;
        for (int cnt = 0; cnt <= 3; cnt++) {
            answer = cnt;
            solution(0, 0);
            if(isFinished){
                System.out.println(cnt);
                return;
            }
        }
        System.out.println(-1);
    }

    private static void solution(int cnt, int x) {
        if(isFinished) return;
        if (cnt == answer) {
            if(check()) isFinished = true;
            return;
        }
        for (int i = x; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if(ladder[i][j] != 0 || ladder[i][j+1] != 0) continue;

                ladder[i][j] = 1;
                ladder[i][j + 1] = 2;
                solution(cnt + 1, i );
                ladder[i][j] = ladder[i][j + 1] = 0;
            }
        }
    }

    private static boolean check() {
        for (int i = 0; i < N; i++) {//세로
            int idx = i;
            for (int j = 0; j < H; j++) { //가로
                if(ladder[j][idx] == 1) idx += 1;
                else if(ladder[j][idx] == 2) idx -= 1;
            }

            if(idx != i) return false;
        }
        return true;
    }
}
