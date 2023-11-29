import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_BOJ_2036_수열의_정수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Long> plus = new ArrayList<>();
        List<Long> minus = new ArrayList<>();
        List<Long> one = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long a = Long.parseLong(br.readLine());

            if(a == 1) one.add(a);
            else if(a > 1) plus.add(a);
            else if(a <= 0) minus.add(a);
        }

        Collections.sort(plus, Collections.reverseOrder());
        Collections.sort(minus);

        BigInteger answer = new BigInteger("0");

        if (plus.size() % 2 == 0) {
            for (int i = 0; i < plus.size(); i += 2) {
                answer = answer.add(BigInteger.valueOf(plus.get(i) * plus.get(i + 1)));
            }
        } else {
            for (int i = 0; i < plus.size() - 1; i += 2) {
                answer = answer.add(BigInteger.valueOf(plus.get(i) * plus.get(i + 1)));
            }
            answer = answer.add(BigInteger.valueOf(plus.get(plus.size() - 1)));
        }

        if (minus.size() % 2 == 0) {
            for (int i = 0; i < minus.size(); i += 2) {
                answer = answer.add(BigInteger.valueOf(minus.get(i) * minus.get(i + 1)));
            }
        } else {
            for (int i = 0; i < minus.size() - 1; i += 2) {
                answer = answer.add(BigInteger.valueOf(minus.get(i) * minus.get(i + 1)));
            }
            answer = answer.add(BigInteger.valueOf(minus.get(minus.size() - 1)));
        }

        answer = answer.add(BigInteger.valueOf(one.size()));

        System.out.println(answer);
    }

}
