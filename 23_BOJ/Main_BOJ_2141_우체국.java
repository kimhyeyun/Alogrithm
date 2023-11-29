import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_BOJ_2141_우체국 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[][] arr = new long[N][2];
        long total = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            arr[i] = new long[]{a, b};
            total += b;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) return (int) (o1[1] - o2[1]);
            return (int) (o1[0] - o2[0]);
        });

        long tmp = 0;
        for (int i = 0; i < N; i++) {
            tmp += arr[i][1];
            if (tmp >= (total + 1) / 2) {
                System.out.println(arr[i][0]);
                return;
            }
        }
    }
}
