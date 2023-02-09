import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_20310_타노스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num = br.readLine();

        int zero = 0, one = 0;
        for (char c : num.toCharArray()) {
            if (c == '0') {
                zero += 1;
            } else {
                one += 1;
            }
        }

        zero /= 2;
        one /= 2;
        String answer = "";

        for (char c : num.toCharArray()) {
            if (c == '0' && zero > 0) {
                answer += c;
                zero -= 1;
            } else if (c == '1' && one == 0) {
                answer += c;
            } else if (c == '1' && one > 0) {
                one -= 1;
            }
        }

        System.out.println(answer);
    }
}
