import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17266_어두운_굴다리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[M];
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = N;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(N, arr, mid)) {
                answer = mid;
                right = mid - 1;
            } else left = mid + 1;
        }

        System.out.println(answer);
    }

    private static boolean isPossible(int N, int[] arr, int height) {
        int prev = 0;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] - height <= prev) prev = arr[i] + height;
            else return false;
        }
        return N - prev <= 0;
    }
}
