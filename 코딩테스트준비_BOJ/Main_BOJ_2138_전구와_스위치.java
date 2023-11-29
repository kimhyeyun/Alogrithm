import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2138_전구와_스위치 {
    static int N, answer;
    static char[][] now;
    static char[] want;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        now = new char[2][N];
        now[0] = br.readLine().toCharArray();
        System.arraycopy(now[0], 0, now[1], 0, N);

        want = br.readLine().toCharArray();

        answer = Integer.MAX_VALUE;

        sol(0, 1, 0); // 0번째 전구 안누르고 시작

        pushSwitch(1, 0);
        sol(1, 1, 1);

        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void sol(int cur, int idx, int cnt) {
        if (idx == N) {
            if (now[cur][idx - 1] == want[idx - 1]) {
                answer = Math.min(answer, cnt);
            }
            return;
        }

        if (now[cur][idx - 1] == want[idx - 1]) {
            sol(cur, idx + 1, cnt);
        } else {
            pushSwitch(cur, idx);
            sol(cur, idx + 1, cnt + 1);
        }
    }

    private static void pushSwitch(int cur, int idx) {
        if (idx == 0) {
            now[cur][idx] = now[cur][idx] == '1' ? '0' : '1';
            now[cur][idx + 1] = now[cur][idx + 1] == '1' ? '0' : '1';
        } else if (idx == N - 1) {
            now[cur][idx] = now[cur][idx] == '1' ? '0' : '1';
            now[cur][idx - 1] = now[cur][idx - 1] == '1' ? '0' : '1';
        } else {
            now[cur][idx - 1] = now[cur][idx - 1] == '1' ? '0' : '1';
            now[cur][idx] = now[cur][idx] == '1' ? '0' : '1';
            now[cur][idx + 1] = now[cur][idx + 1] == '1' ? '0' : '1';
        }
    }
}
