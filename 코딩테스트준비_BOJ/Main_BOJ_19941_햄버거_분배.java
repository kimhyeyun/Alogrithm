import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_19941_햄버거_분배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());
        int answer = 0;

        char[] arr = br.readLine().toCharArray();
        boolean[] isEaten = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (arr[i] == 'P') {
                int start = Math.max(0, i - K);
                int end = Math.min(N - 1, i + K);

                for (int j = start; j <= end; j++) {
                    if (arr[j] == 'H' && !isEaten[j]) {
                        isEaten[j] = true;
                        answer += 1;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
