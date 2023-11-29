import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_18185_라면_사기_small {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int index = 0, answer = 0;
        while (index < N) {
            if (arr[index] > 0) {
                int temp = arr[index];
                answer += 3 * temp;

                temp = Math.min(temp, arr[index + 1]);
                answer += 2 * temp;
                arr[index + 1] -= temp;

                temp = Math.min(temp, arr[index + 2] - Math.min(arr[index + 1], arr[index + 2]));
                answer += 2 * temp;
                arr[index + 2] -= temp;
            }
            index += 1;
        }
        System.out.println(answer);

    }
}
