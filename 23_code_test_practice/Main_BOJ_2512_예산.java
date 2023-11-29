import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_2512_예산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int sum = 0;
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        if (sum <= M) {
            System.out.println(arr[arr.length - 1]);
            return;
        }

        int left = 0;
        int right = arr[arr.length - 1];
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            sum = 0;

            for (int i = 0; i < arr.length; i++) {
                sum += Math.min(arr[i], mid);
            }

            if(sum <= M) answer = Math.max(answer, mid);

            if(sum > M) right = mid - 1;
            else left = mid + 1;
        }

        System.out.println(answer);
    }
}
