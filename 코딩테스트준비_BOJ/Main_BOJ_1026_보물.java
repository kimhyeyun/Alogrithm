import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1026_보물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += (A[i] * B[N - i - 1]);
        }
        System.out.println(ans);
    }
}
