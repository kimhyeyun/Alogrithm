import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20922_겹치는_건_싫어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int[] count = new int[max + 1];

        int left = 0, right = 0;
        int answer = Integer.MIN_VALUE;
        while (right < N) {
            while (right < N && count[arr[right]] + 1 <= K) {
                count[arr[right]] += 1;
                right += 1;
            }

            int len = right - left;
            answer = Math.max(answer, len);
            count[arr[left]] -= 1;
            left += 1;
        }

        System.out.println(answer);
    }
}
