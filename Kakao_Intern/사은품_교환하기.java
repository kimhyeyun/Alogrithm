import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사은품_교환하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            long N = Long.parseLong(stringTokenizer.nextToken());
            long M = Long.parseLong(stringTokenizer.nextToken());

            if (N + M < 12 && N < 5) {
                System.out.println(0);
                continue;
            }

            if ((N + M) % 12 == 0) {
                long cnt = (N + M) / 12;

                if (N / cnt < 5) {
                    System.out.println(N / 5);
                } else {
                    System.out.println(cnt);
                }
            } else {
                System.out.println(Math.min(N / 5, (N + M) / 12));
            }
        }
    }
}
