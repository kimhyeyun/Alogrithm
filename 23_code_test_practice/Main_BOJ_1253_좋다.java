import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1253_좋다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int answer = 0;

        for (int i = 0; i < N; i++) {
            int a = arr[i];

            int left = 0, right = N - 1;
            int sum = 0;

            while (left < right) {
                sum = arr[left] + arr[right];

                if (sum == a) {
                    if(i == left) left += 1;
                    else if(i == right) right -= 1;
                    else {
                        answer += 1;
                        break;
                    }
                }

                if(arr[left] + arr[right] > a) right -= 1;
                else if(arr[left] + arr[right] < a) left += 1;
            }
        }
        System.out.println(answer);
    }
}
