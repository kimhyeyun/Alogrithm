import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_21921_블로그 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        /*
        * dp[0] dp[1] dp[2] dp[3] dp[4]   dp[5]
        *    0   1     5       7   12      13
        *
        * X = 2
        * i = 2 부터 시작
        * dp[i] - dp[i-2] 5
        * i = 3 dp[3] - dp[1] 6
        * i = 4 dp[4] - dp[2] 7
        * i = 5 dp[5] - dp[3] 6
        *
        * dp[0] dp[1] dp[2] dp[3] dp[4] dp[5] dp[6] dp[7]
        * 0      1       2    3   4      5     10  11
        *
        * X = 5
        * i = 5 부터 시작
        * dp[i] - dp[i-5] 5
        * i = 6 dp[6] - dp[1]  9
        * i = 7 dp[7] - dp[2] 9
        *
        * */

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int X = Integer.parseInt(stringTokenizer.nextToken());
        int[] sum = new int[N + 1];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int x = Integer.parseInt(stringTokenizer.nextToken());
            sum[i] = sum[i - 1] + x;
        }

        int answer = Integer.MIN_VALUE;
        int count = 1;
        for (int i = X; i < N + 1; i++) {
            int tmp = sum[i] - sum[i - X];
            if (tmp > answer) {
                count = 1;
                answer = tmp;
            } else if (tmp == answer) {
                count += 1;
            }
        }

        if (answer <= 0) {
            System.out.println("SAD");
        } else {
            System.out.println(answer);
            System.out.println(count);
        }
    }
}
