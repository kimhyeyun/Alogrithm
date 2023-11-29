import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1943_동전_분배 {
    static class Coin {
        int amount;
        int count;

        public Coin(int amount, int count) {
            this.amount = amount;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 3; i++) {
            int N = Integer.parseInt(br.readLine());
            List<Coin> coins = new LinkedList<>();
            int total = 0;
            boolean[] dp = new boolean[100001];

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                coins.add(new Coin(a, c));
                total += (a * c);

                for (int k = 1; k <= c; k++) dp[a * k] = true;
            }

            if (total % 2 == 1) {
                System.out.println(0);
                continue;
            } else if (dp[total / 2]) {
                System.out.println(1);
                continue;
            }

            dp[0] = true;
            for (int j = 0; j < N; j++) {
                int a = coins.get(j).amount;
                int c = coins.get(j).count;

                for (int k = total / 2; k >= a; k--) {
                    if (dp[k - a]) {
                        for (int z = 1; z <= c; z++) {
                            if (k - a + a * z > total / 2) break;
                            dp[k - a + a * z] = true;
                        }
                    }
                }
            }
            System.out.println(dp[total / 2] ? 1 : 0);
        }
    }
}
