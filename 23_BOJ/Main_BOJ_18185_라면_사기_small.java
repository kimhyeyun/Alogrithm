import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_18185_라면_사기_small {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int index = 0;
        int answer = 0;

        while (index < N) {
            if (A[index] > 0) {
                int tmp = A[index];
                answer += 3 * tmp;
                tmp = Math.min(tmp, A[index + 1]);
                answer += 2 * tmp;
                A[index + 1] -= tmp;
                tmp = Math.min(tmp, A[index + 2] - Math.min(A[index + 1], A[index + 2]));
                answer += 2 * tmp;
                A[index + 2] -= tmp;
            }
            index += 1;
        }
        System.out.println(answer);

    }
}
