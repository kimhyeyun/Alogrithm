import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14888_연산자_끼워넣기 {
    static int N, maxAnswer, minAnswer;
    static int[] nums;
    static int[] operators;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        maxAnswer = Integer.MIN_VALUE;
        minAnswer = Integer.MAX_VALUE;

        nums = new int[N];
        operators = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        DFS(0, nums[0]);

        System.out.println(maxAnswer);
        System.out.println(minAnswer);
    }

    private static void DFS(int idx, int sum) {
        if (idx == N - 1) {
            maxAnswer = Math.max(maxAnswer, sum);
            minAnswer = Math.min(minAnswer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if(operators[i] <= 0) continue;

            operators[i] -= 1;

            if(i == 0) DFS(idx + 1, sum + nums[idx + 1]);
            else if(i == 1) DFS(idx + 1, sum - nums[idx + 1]);
            else if(i == 2) DFS(idx + 1, sum * nums[idx + 1]);
            else DFS(idx + 1, sum / nums[idx + 1]);

            operators[i] += 1;
        }
    }
}
