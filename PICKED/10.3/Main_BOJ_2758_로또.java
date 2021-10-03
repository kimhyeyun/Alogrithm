import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_2758_로또 {
    // DFS 로 풀이 시 메모리 초과 발생 
    /* static boolean[] isVisted;
    static int n,m;
    static long answer;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            String[] nm = br.readLine().split(" ");
            n = Integer.parseInt(nm[0]);
            m = Integer.parseInt(nm[1]);
            answer = 0;

            isVisted = new boolean[m+1];
            for(int i = 1; i <= m; i++){
                isVisted[i] = true;
                DFS(i, 1, String.valueOf(i));
                isVisted[i] = false;
            }

            System.out.println(answer);
        }      
    }
    private static void DFS(int now, int cnt, String str) {

        if(cnt == n){
            answer++;
            return;
        }

        if(cnt > n)
            return;

        for(int i = now*2; i <= m ; i++){
            if(!isVisted[i]){
                isVisted[i] = true;
                DFS(i*2, cnt+1, str+i);
                isVisted[i] = false;
            }
        }
    } */

    // dp와 memorization 으로 해결해주어야함
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            int N, M;
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(stringTokenizer.nextToken());
            M = Integer.parseInt(stringTokenizer.nextToken());

            long[][] dp = new long[N+1][M+1];

            for(int i = 0 ; i < M+1; i++){
                dp[0][i] = 1;
            }

            for(int n = 1; n < N+1 ; n++){
                for(int m = 1; m < M+1 ; m++){
                    // n번째 숫자로 m을 선택하는 경우 + 선택하지 않는 경우
                    dp[n][m] = dp[n-1][m/2] + dp[n][m-1];
                }
            }

            System.out.println(dp[N][M]);
        }
    }
}
