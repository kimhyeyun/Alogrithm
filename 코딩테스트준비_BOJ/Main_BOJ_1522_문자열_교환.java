import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1522_문자열_교환 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int aCount = 0;
        int bCount = 0;
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') {
                aCount += 1;
            }
        }

        for (int i = 0; i < str.length(); i++) {
            bCount = 0;
            for (int j = i; j < i + aCount; j++) {
                if (str.charAt(j % str.length()) == 'b') {
                    bCount += 1;
                }
            }
            answer = Math.min(answer, bCount);
        }

        System.out.println(answer);
    }
}
