import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_13458_시험_감독 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] testRoom = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            testRoom[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int answer = 0;
        for (int i = 0; i < N; i++) {
            testRoom[i] -= B;
            answer += 1;

            if (testRoom[i] > 0) {
                int m = testRoom[i] % C;
                int c = testRoom[i] / C;

                if(m > 0) c += 1;

                answer += c;
            }
        }
        System.out.println(answer);
    }
}
