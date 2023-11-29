import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2891_카약과_강풍 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        Arrays.fill(arr, 1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            arr[Integer.parseInt(st.nextToken())] -= 1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            arr[Integer.parseInt(st.nextToken())] += 1;
        }

        for (int i = 1; i <= N; i++) {
            if (arr[i] == 0) {
                if (i == 1) {
                    if (arr[i + 1] == 2) {
                        arr[i] += 1;
                        arr[i + 1] -= 1;
                    }
                } else if (i == N) {
                    if (arr[i - 1] == 2) {
                        arr[i] += 1;
                        arr[i - 1] -= 1;
                    }
                } else {
                    if (arr[i - 1] == 2) {
                        arr[i] += 1;
                        arr[i - 1] -= 1;
                    } else if (arr[i + 1] == 2) {
                        arr[i] += 1;
                        arr[i + 1] -= 1;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (arr[i] == 0) answer += 1;
        }

        System.out.println(answer);
    }
}
