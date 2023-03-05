import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_11964_Fruit_Feast {
    static int T, A, B;
    static boolean[] dp;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        dp = new boolean[T + 1];
        max = 0;

        DFS(0, false);

        System.out.println(max);
    }

    private static void DFS(int tmp, boolean flag) {
        if(dp[tmp]) return;

        max = Math.max(tmp, max);
        dp[tmp] = true;

        int sum = tmp + A;

        if (sum <= T) {
            if(!flag) DFS(sum / 2, true);
            DFS(sum, flag);
        }

        sum = tmp + B;

        if(sum <= T){
            if(!flag) DFS(sum / 2, true);
            DFS(sum, flag);
        }
    }
}
