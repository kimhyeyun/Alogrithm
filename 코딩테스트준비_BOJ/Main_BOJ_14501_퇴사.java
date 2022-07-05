import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14501_퇴사 {
    static int N, ans = 0;
    static int[] time, pay;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        pay = new int[N + 1];


        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(stringTokenizer.nextToken());
            pay[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            Solve(i, 0, pay[i]);
        }
        System.out.println(ans);
    }

    private static void Solve(int idx, int sum, int add) {
        if (idx == N + 1) {
            ans = Math.max(ans, sum);
            return;
        } else if (idx > N + 1) {
            ans = Math.max(ans, sum - add);
            return;
        }

        for (int i = idx + time[idx]; i <= N + time[idx]; i++) {
            Solve(i, sum + pay[idx], pay[idx]);
        }
    }
}
