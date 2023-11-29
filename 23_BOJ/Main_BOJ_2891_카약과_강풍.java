import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2891_카약과_강풍 {
    public static void main(String[] args) throws IOException {
        System.out.println(solution());
    }

    private static int solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = 1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[n] -= 1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[n] += 1;
        }

        for (int i = 1; i <= N; i++) {
            if (arr[i] == 0) {
                if (i - 1 > 0 && arr[i - 1] == 2) {
                    arr[i] += 1;
                    arr[i - 1] -= 1;
                } else if (i + 1 <= N && arr[i + 1] == 2) {
                    arr[i] += 1;
                    arr[i + 1] -= 1;
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if(arr[i] == 0) count += 1;
        }

        return count;
    }
}
