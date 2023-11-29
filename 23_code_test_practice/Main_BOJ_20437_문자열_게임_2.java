import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_20437_문자열_게임_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if (K == 1) {
                System.out.println("1 1");
                continue;
            }

            int[] alphabet = new int[26];
            for (int i = 0; i < W.length(); i++) {
                alphabet[W.charAt(i) - 'a'] += 1;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < W.length(); i++) {
                if(alphabet[W.charAt(i) - 'a'] < K) continue;

                int count = 1;
                for (int j = i + 1; j < W.length(); j++) {
                    if(W.charAt(j) == W.charAt(i)) count += 1;

                    if (count == K) {
                        min = Math.min(min, j - i + 1);
                        max = Math.max(max, j - i + 1);
                        break;
                    }
                }
            }

            if(min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) System.out.println(-1);
            else System.out.println(min + " " + max);
        }
    }
}
