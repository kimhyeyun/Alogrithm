import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_2879_코딩은_예쁘게 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] target = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            target[i] = Integer.parseInt(st.nextToken()) - target[i];
        }

        int prev = 0, answer = 0;
        if (N > 1) {
            prev = target[0];

            for (int i = 1; i < N; i++) {
                if (prev * target[i] < 0) {
                    answer += Math.abs(prev);
                } else if (Math.abs(prev) >= Math.abs(target[i])) {
                    answer += (Math.abs(prev) - Math.abs(target[i]));
                }

                prev = target[i];
            }
        } else {
            answer = target[0];
        }

        answer += Math.abs(prev);
        System.out.println(answer);
    }
}
