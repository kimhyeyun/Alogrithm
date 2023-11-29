import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2096_내려가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] minDP = new int[N][3];
        int[][] maxDP = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (i == 0) {
                minDP[i][0] = maxDP[i][0] = a;
                minDP[i][1] = maxDP[i][1] = b;
                minDP[i][2] = maxDP[i][2] = c;
            } else {
                maxDP[i][0] = Math.max(maxDP[i - 1][0], maxDP[i - 1][1]) + a;
                maxDP[i][1] = Math.max(Math.max(maxDP[i - 1][0], maxDP[i - 1][1]), maxDP[i - 1][2]) + b;
                maxDP[i][2] = Math.max(maxDP[i - 1][1], maxDP[i - 1][2]) + c;

                minDP[i][0] = Math.min(minDP[i - 1][0], minDP[i - 1][1]) + a;
                minDP[i][1] = Math.min(Math.min(minDP[i - 1][0], minDP[i - 1][1]), minDP[i - 1][2]) + b;
                minDP[i][2] = Math.min(minDP[i - 1][1], minDP[i - 1][2]) + c;
            }


        }

        int max = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, maxDP[N - 1][i]);
            min = Math.min(min, minDP[N - 1][i]);
        }

        System.out.println(max + " " + min);
    }
}
