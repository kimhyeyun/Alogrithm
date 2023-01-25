import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20922_겹치는_건_싫어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        int[] count = new int[100001];
        int[] numbers = new int[N];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int left = 0, right = 0;
        int answer = Integer.MIN_VALUE;
        while (right < numbers.length) {
            while (right < numbers.length && count[numbers[right]] + 1 <= K) {
                count[numbers[right]] += 1;
                right += 1;
            }

            int len = right - left;
            answer = Math.max(answer, len);
            count[numbers[left]] -= 1;
            left += 1;
        }

        System.out.println(answer);
    }
}
