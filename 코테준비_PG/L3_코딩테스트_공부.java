public class L3_코딩테스트_공부 {
    public static int solution(int alp, int cop, int[][] problems) {
        int goalAlp = 0;
        int goalCop = 0;
        for (int[] problem : problems) {
            goalAlp = Math.max(goalAlp, problem[0]);
            goalCop = Math.max(goalCop, problem[1]);
        }

        if (goalAlp <= alp && goalCop <= cop) {
            return 0;
        }
        if (goalAlp <= alp) {
            goalAlp = alp;
        }
        if(goalCop <= cop){
            goalCop = cop;
        }

        int[][] dp = new int[goalAlp + 2][goalCop + 2];
        for (int i = alp; i <= goalAlp; i++) {
            for (int j = cop; j <= goalCop; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[alp][cop] = 0;

        for (int i = alp; i <= goalAlp; i++) {
            for (int j = cop; j <= goalCop; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                for (int[] problem : problems) {
                    if (i >= problem[0] && j >= problem[1]) {
                        if (i + problem[2] > goalAlp && j + problem[3] > goalCop) {
                            dp[goalAlp][goalCop] = Math.min(dp[goalAlp][goalCop], dp[i][j] + problem[4]);
                        }else if(i + problem[2] > goalAlp){
                            dp[goalAlp][j + problem[3]] = Math.min(dp[goalAlp][j + problem[3]], dp[i][j] + problem[4]);
                        } else if (j + problem[3] > goalCop) {
                            dp[i + problem[2]][goalCop] = Math.min(dp[i + problem[2]][goalCop], dp[i][j] + problem[4]);
                        } else if (i + problem[2] <= goalAlp && j + problem[3] <= goalCop) {
                            dp[i + problem[2]][j + problem[3]] = Math.min(dp[i + problem[2]][j + problem[3]], dp[i][j] + problem[4]);
                        }
                    }
                }
            }
        }
        return dp[goalAlp][goalCop];
    }

    public static void main(String[] args) {
//        int[][] problems = {{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}};
        int[][] problems = {{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}};

        System.out.println(solution(0, 0, problems));
    }
}
