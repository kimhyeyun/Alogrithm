import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_5073_삼각형과_세_변 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 0 && b == 0 && c == 0) break;
            if (a == b && b == c) {
                System.out.println("Equilateral");
                continue;
            }

            int max = a, index = 0;
            if (max < b) {
                max = b;
                index = 1;
            }
            if (max < c) {
                max = c;
                index = 2;
            }

            int sum = 0;
            if(index == 0) sum = b + c;
            else if(index == 1) sum = a + c;
            else sum = a + b;

            if (sum <= max) {
                System.out.println("Invalid");
                continue;
            }

            if (a != b && b != c && a != c) {
                System.out.println("Scalene");
                continue;
            }

            if(a == b || a == c || b == c) System.out.println("Isosceles");
        }
    }
}
