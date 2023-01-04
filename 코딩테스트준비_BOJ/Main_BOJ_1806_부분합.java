import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int S = Integer.parseInt(stringTokenizer.nextToken());
        int[] arr = new int[N + 1];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right <= N) {
            if (sum >= S) {
                sum -= arr[left++];
                answer = Math.min(answer, right - left + 1);
            } else if (sum < S) {
                sum += arr[right++];
            }
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}
