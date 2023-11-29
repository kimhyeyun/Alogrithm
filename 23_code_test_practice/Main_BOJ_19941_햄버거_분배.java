import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_19941_햄버거_분배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] str = br.readLine().toCharArray();
        boolean[] isEaten = new boolean[N];

        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (str[i] == 'P') {
                int start = Math.max(0, i - K);
                int end = Math.min(i + K, N - 1);

                for (int j = start; j <= end; j++) {
                    if(str[j] == 'P' || isEaten[j]) continue;

                    isEaten[j] = true;
                    answer += 1;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
