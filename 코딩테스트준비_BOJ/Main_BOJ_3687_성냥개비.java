import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class Main_BOJ_3687_성냥개비 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        long[] minDp = getMinDp();

        while (testCase-- > 0) {
            int num = Integer.parseInt(br.readLine());



            String line = "";
            if (num % 2 == 0) {
                for (int i = 0; i < num / 2; i++) {
                    line += "1";
                }
            }else{
                line = "7";
                for (int i = 0; i < num / 2 - 1; i++) {
                    line += "1";
                }
            }

            System.out.println(minDp[num] + " " + line);
        }
    }

    private static long[] getMinDp() {
        long[] minDp = new long[101];

        Arrays.fill(minDp, Long.MAX_VALUE);
        minDp[2] = 1;
        minDp[3] = 7;
        minDp[4] = 4;
        minDp[5] = 2;
        minDp[6] = 6;
        minDp[7] = 8;
        minDp[8] = 10;

        String[] add = {"", "", "1", "7", "4", "2", "0", "8"};

        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String line = minDp[i - j] + add[j];
                minDp[i] = Math.min(minDp[i], Long.parseLong(line));
            }
        }
        return minDp;
    }
}
