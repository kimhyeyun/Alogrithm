import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_20310_타노스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int countOfZero = 0, countOfOne = 0;
        for (char c : str.toCharArray()) {
            if(c == '0') countOfZero += 1;
            else countOfOne += 1;
        }

        countOfZero /= 2;
        countOfOne /= 2;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (countOfOne > 0 && str.charAt(i) == '1') {
                countOfOne -= 1;
                continue;
            }
            sb.append(str.charAt(i));
        }

        String s = sb.toString();
        sb = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            if (countOfZero > 0 && s.charAt(i) == '0'){
                countOfZero -= 1;
                continue;
            }
            sb.append(s.charAt(i));
        }

        System.out.println(sb.reverse());
    }
}
