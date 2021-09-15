import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17209_카우버거알바생 {
    static int[][][] DP;
    static int N, M, K;
    static int[] cheeseBurger;
    static int[] frenchFry;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        DP = new int[M+1][K+1][N+1];
        cheeseBurger = new int[N+1];
        frenchFry = new int[N+1];

        for(int i = 1 ; i <= N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            int f = Integer.parseInt(stringTokenizer.nextToken());

            cheeseBurger[i] = c;
            frenchFry[i] = f;
        }

        for(int i = 0 ; i < M+1 ; i++){
            for(int j =0 ; j < K+1 ; j++){
                for(int k = 0 ; k < N+1; k++){
                    DP[i][j][k] = -1;
                }
            }
        }

        System.out.println(solve(M, K, 0));

    }
    private static int solve(int c, int f, int idx) {
        if(idx == N)
            return 0;
        // 이 부분이 없으면 시간 초과!
        if(DP[c][f][idx] > -1)
            return DP[c][f][idx];

        int choose = 0;
        int noChoose = 0;
        if(cheeseBurger[idx+1] <= c && frenchFry[idx+1] <= f){
            // idx+1번째 주문 받음
            choose = solve(c-cheeseBurger[idx+1], f-frenchFry[idx+1], idx+1) + 1;
        }       
        // idx+1 번째 주문 안받음
        noChoose = solve(c, f, idx+1);
        DP[c][f][idx] = Math.max(choose, noChoose);
        
        return DP[c][f][idx];
    }
}
