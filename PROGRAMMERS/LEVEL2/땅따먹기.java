public class 땅따먹기 {

    public static int solution(int[][] land) {
        int answer = 0;
        int[][] DP = land;

      /*   for(int i = 0; i < land.length; i++){
            for(int j = 0 ; j < 4; j++){
                DP[i][j] = land[i][j];
            }
        } */

        for(int i = 1 ; i < land.length; i++){
            DP[i][0] += Math.max(land[i-1][1], Math.max(land[i-1][2], land[i-1][3]));
            DP[i][1] += Math.max(land[i-1][0], Math.max(land[i-1][2], land[i-1][3]));
            DP[i][2] += Math.max(land[i-1][0], Math.max(land[i-1][1], land[i-1][3]));
            DP[i][3] += Math.max(land[i-1][0], Math.max(land[i-1][1], land[i-1][2]));
        }

        answer = Math.max(DP[DP.length-1][0], Math.max(DP[DP.length-1][1], Math.max(DP[DP.length-1][2], DP[DP.length-1][3])));

        return answer;
    }
    public static void main(String[] args) {
        int[][] l = {{1,2,3,5},{5,6,7,8},{4,3,2,1}};

        System.out.println(solution(l));
    }
}
