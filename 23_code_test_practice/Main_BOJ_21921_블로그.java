import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_21921_블로그 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] sum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int x = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + x;
        }

        int answer = 0;
        int count = 1;

        for (int i = X; i < N + 1; i++) {
            int tmp = sum[i] - sum[i - X];
            if (tmp > answer) {
                count = 1;
                answer = tmp;
            }else if(tmp == answer) count += 1;
        }

        if(answer <= 0) System.out.println("SAD");
        else {
            System.out.println(answer);
            System.out.println(count);
        }
    }
}
