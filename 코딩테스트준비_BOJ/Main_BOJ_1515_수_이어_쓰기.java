import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1515_수_이어_쓰기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int idx = 0, base = 0;

        while (base++ <= 30000) {
            String tmp = String.valueOf(base);
            for (int i = 0; i < tmp.length(); i++) {
                if(tmp.charAt(i) == s.charAt(idx)) idx += 1;
                if (idx == s.length()) {
                    System.out.println(base);
                    return;
                }
            }
        }
    }
}
