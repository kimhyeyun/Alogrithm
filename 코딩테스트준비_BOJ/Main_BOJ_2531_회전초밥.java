import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2531_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int d = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());
        int c = Integer.parseInt(stringTokenizer.nextToken());

        int[] susi = new int[N];
        int[] choice = new int[d + 1];
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            susi[i] = x;
        }

        int cnt = 0;
        int left = 0, right = K - 1;
        for (int i = left; i <= right; i++) {
            if (choice[susi[i]] == 0) cnt += 1;
            choice[susi[i]] += 1;
        }

        if (choice[c] == 0) {
            answer = Math.max(answer, cnt + 1);
        }else {
            answer = Math.max(answer, cnt);
        }

        while (left < N) {
            choice[susi[left]] -= 1;
            if(choice[susi[left]] == 0) cnt -= 1;

            left += 1;
            right = right + 1 == N ? 0 : right + 1;

            if(choice[susi[right]] == 0) cnt += 1;
            choice[susi[right]] += 1;

            if (choice[c] == 0) {
                answer = Math.max(answer, cnt + 1);
            }else {
                answer = Math.max(answer, cnt);
            }

        }

        System.out.println(answer);
    }
}
