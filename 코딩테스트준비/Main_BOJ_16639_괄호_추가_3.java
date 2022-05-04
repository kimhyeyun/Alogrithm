import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_16639_괄호_추가_3 {

    static int N;
    static String exp;
    static int[][] max, min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        exp = br.readLine();

        max = new int[N][N];
        min = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(max[i], Integer.MIN_VALUE);
            Arrays.fill(min[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; i++) {
            if (Character.isDigit(exp.charAt(i))) {
                max[i][i] = min[i][i] = exp.charAt(i) - '0';
            }
        }

        for (int j = 2; j < N; j += 2) {
            for (int i = 0; i < N - j; i += 2) {

            }
        }
    }

}
