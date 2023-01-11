import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_20437_문자열_게임_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if (K == 1) {
                System.out.println("1 1");
                continue;
            }

            int[] alpha = new int[26];
            for (int i = 0; i < str.length(); i++) {
                alpha[str.charAt(i) - 'a'] += 1;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < str.length(); i++) {
                if(alpha[str.charAt(i) - 'a'] < K) {
                    continue;
                }

                int cnt = 1;
                for (int j = i + 1; j < str.length(); j++) {
                    if (str.charAt(j) == str.charAt(i)) {
                        cnt += 1;
                    }
                    if (cnt == K) {
                        min = Math.min(min, j - i + 1);
                        max = Math.max(max, j - i + 1);
                        break;
                    }
                }
            }
            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(min + " " + max);
            }
        }
    }
}
