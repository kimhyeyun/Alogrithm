import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_13458_시험_감독_re {
    static int N, B, C;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        A = new int[N];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        B = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        long ans = N;
        for (int i = 0; i < N; i++) {
            A[i] -= B;

            if (A[i] > 0) {
                ans += (A[i] / C);
                if (A[i] % C != 0) ans += 1;
            }
        }

        System.out.println(ans);
    }
}
