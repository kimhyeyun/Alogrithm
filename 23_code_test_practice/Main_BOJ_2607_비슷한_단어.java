import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2607_비슷한_단어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String word = br.readLine();
        int[] alphabet = new int[26];

        for (char c : word.toCharArray()) {
            alphabet[c - 'A'] += 1;
        }

        int answer = 0;
        for (int i = 0; i < N - 1; i++) {
            String s = br.readLine();

            int[] tmp = new int[26];
            System.arraycopy(alphabet, 0, tmp, 0, 26);

            int count = 0;
            for (char c : s.toCharArray()) {
                if (tmp[c - 'A'] > 0) {
                    count += 1;
                    tmp[c - 'A'] -= 1;
                }
            }

            if (s.length() == word.length()) {
                if(count == s.length() || count == s.length() - 1) answer += 1;
            } else if (s.length() - word.length() == 1) {
                if(count == word.length()) answer += 1;
            } else if (word.length() - s.length() == 1 && count == s.length()) {
                answer += 1;
            }
        }

        System.out.println(answer);
    }
}
