import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_13305_주유소 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        long[] dist = new long[N];
        long[] priceOfLitter = new long[N + 1];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i < N; i++) {
            dist[i] = Long.parseLong(stringTokenizer.nextToken());
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            priceOfLitter[i] = Long.parseLong(stringTokenizer.nextToken());
        }

        long minPrice = priceOfLitter[1];
        long answer = 0;
        for (int i = 1; i < N; i++) {
            minPrice = Math.min(minPrice, priceOfLitter[i]);
            answer += (dist[i] * minPrice);
        }

        System.out.println(answer);
    }
}
