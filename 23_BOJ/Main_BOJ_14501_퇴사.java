import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14501_퇴사 {
    static int N, answer;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        answer = Integer.MIN_VALUE;

        sol(1, 0);

        System.out.println(answer);
    }

    private static void sol(int day, int sum) {
        if (day > N) {
            answer = Math.max(answer, sum);
            return;
        }

        if(day + arr[day][0] > N + 1) sol(day + arr[day][0], sum);
        else sol(day + arr[day][0], sum + arr[day][1]);

        sol(day + 1, sum);
    }
}
