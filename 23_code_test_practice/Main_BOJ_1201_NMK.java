import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1201_NMK {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(solve(N, M, K));
    }

    private static String solve(int n, int m, int k) {
        if (m + k - 1 > n || m * k < n) return "-1";

        int[] groupSize = new int[m];
        groupSize[0] = k;
        n -= k;

        if (m - 1 > 0) {
            int div = n / (m - 1);
            int remain = n % (m - 1);

            for (int i = 1; i < m; i++) {
                if (remain-- > 0) groupSize[i] = div + 1;
                else groupSize[i] = div;
            }
        }

        StringBuilder sb = new StringBuilder();
        int sIndex = 0, eIndex = 0;
        for (int i = 0; i < m; i++) {
            sIndex = eIndex;
            eIndex += groupSize[i];

            for (int j = eIndex - 1; j >= eIndex - groupSize[i]; j--) {
                sb.append(j + 1).append(" ");
            }
        }
        return sb.toString();
    }
}
