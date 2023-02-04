import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_5073_삼각형과_세_변 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            int s3 = Integer.parseInt(st.nextToken());


            if (s1 == 0 && s2 == 0 && s3 == 0) {
                break;
            }

            int max = Math.max(s1, Math.max(s2, s3));
            int sum = s1 + s2 + s3 - max;

            if (sum <= max) {
                System.out.println("Invalid");
            } else {
                if (isEqual(s1, s2) && isEqual(s1, s3) && isEqual(s2, s3)) {
                    System.out.println("Equilateral");
                } else if ((isEqual(s1, s2) && !isEqual(s1, s3) && !isEqual(s2, s3)) ||
                        (isEqual(s1, s3) && !isEqual(s1, s2) && !isEqual(s2, s3)) ||
                        (isEqual(s2, s3) && !isEqual(s1, s2) && !isEqual(s1, s3))
                ) {
                    System.out.println("Isosceles");
                } else {
                    System.out.println("Scalene");
                }
            }
        }
    }

    private static boolean isEqual(int s1, int s2) {
        if (s1 == s2) {
            return true;
        }
        return false;
    }
}
