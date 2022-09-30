import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_14501_퇴사{
    static int N, answer;
    static int[] time, price;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());

        time = new int[N + 1];
        price = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());
        }

        solution(1, 0);

        System.out.println(answer);
    }

    private static void solution(int day, int sum) {
        if (day > N) {
            answer = Math.max(answer, sum);
            return;
        }

        if(day + time[day] > N + 1) solution(day + time[day], sum);
        else solution(day + time[day], sum + price[day]);

        solution(day + 1, sum);
    }
}
