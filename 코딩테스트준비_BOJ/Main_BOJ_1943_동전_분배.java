import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1943_동전_분배 {
    static class Coin {
        int v, q;

        public Coin(int v, int q) {
            this.v = v;
            this.q = q;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        for (int t = 0; t < 3; t++) {
            int N = Integer.parseInt(br.readLine());
            boolean[] dp = new boolean[100001];
            List<Coin> coins = new ArrayList<>();

            int total = 0;
            for (int i = 0; i < N; i++) {
                stringTokenizer = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(stringTokenizer.nextToken());
                int q = Integer.parseInt(stringTokenizer.nextToken());

                coins.add(new Coin(v, q));

                total += (v * q);
                for (int j = 1; j <= q; j++) {
                    dp[v * j] = true;
                }
            }

            if (total % 2 == 1) {
                System.out.println(0);
                continue;
            } else if (dp[total / 2]) {
                System.out.println(1);
                continue;
            }

            dp[0] = true;
            for (int i = 0; i < N; i++) {
                int v = coins.get(i).v;
                int q = coins.get(i).q;

                for (int j = total / 2; j >= v; j--) {
                    if (dp[j - v]) {
                        for (int k = 1; k <= q; k++) {
                            if (j - v + v * k > total / 2) break;
                            dp[j - v + v * k] = true;
                        }
                    }
                }
            }
            System.out.println(dp[total / 2] ? 1 : 0);

        }
    }
}
