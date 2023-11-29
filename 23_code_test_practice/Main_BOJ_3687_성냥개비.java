import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_3687_성냥개비 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        long[] minDP = new long[101];
        String[] maxDP = new String[101];

        Arrays.fill(minDP, Long.MAX_VALUE);
        minDP[2] = 1;
        minDP[3] = 7;
        minDP[4] = 4;
        minDP[5] = 2;
        minDP[6] = 6;
        minDP[7] = 8;
        minDP[8] = 10;

        String[] add = {"1", "7", "4", "2", "0", "8"};
        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String line = minDP[i - j] + add[j - 2];
                minDP[i] = Math.min(Long.parseLong(line), minDP[i]);
            }
        }

        String[] add2 = {"0", "0", "1", "7", "4", "2", "6", "8"};
        maxDP[2] = "1";
        for (int i = 3; i <= 100; i++) {
            StringBuilder line = new StringBuilder();
            if (i % 2 == 0) line.append("1".repeat(i / 2));
            else {
                int val = i / 2 - 1;
                line.append("1".repeat(val));
                line.insert(0, add2[i - val * 2]);
            }
            maxDP[i] = line.toString();
        }
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            System.out.println(minDP[N] + " " + maxDP[N]);
        }

    }
}
