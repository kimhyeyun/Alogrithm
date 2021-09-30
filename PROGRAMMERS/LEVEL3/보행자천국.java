public class 보행자천국 {
    static int MOD = 20170805;
    public static int solution(int m, int n, int[][] cityMap) {

        int[][][] dp = new int[m][n][2];    // 0 : 위 1 : 왼쪽

        dp[0][0][0] = 1;
        dp[0][0][1] = 1;

        // 첫 행 초기화 -> 왼쪽에서만 올 수 있음
        for(int i = 1; i < n ; i++){
            if(cityMap[0][i] != 1)
                dp[0][i][1] = dp[0][i-1][1];
        }
        // 첫 열 초기화 -> 위에서만 올 수 있음
        for(int i = 1; i < m ;i++){
            if(cityMap[i][0] != 1)
                dp[i][0][0] = dp[i-1][0][0];
        }

        for(int i = 1; i < m ; i++){
            for(int j = 1; j < n ; j++){
                // 윗 칸
                if(cityMap[i-1][j] != 1){
                    // 좌/우회전 불가 -> 그대로 윗 칸
                    if(cityMap[i-1][j] == 2)
                        dp[i][j][0] = (dp[i-1][j][0] % MOD);
                    // 회전 가능
                    else
                        dp[i][j][0] = (dp[i-1][j][0] + dp[i-1][j][1]) % MOD;
                }

                // 왼쪽 칸
                if(cityMap[i][j-1] != 1){
                    if(cityMap[i][j-1] == 2)
                        dp[i][j][1] = dp[i][j-1][1];
                    else
                        dp[i][j][1] = (dp[i][j-1][0] + dp[i][j-1][1]) % MOD;
                }
            }
        }

        return (dp[m-1][n-1][0] + dp[m-1][n-1][1]) % MOD;
    }

    public static void main(String[] args) {
        int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0},{1, 0, 0, 2, 2, 0}};

        System.out.println(solution(3, 5, cityMap));
    }
}
