public class L2_야간_전술보행 {
    public int solution(int distance, int[][] scope, int[][] times) {
        int answer = distance;

        for (int i = 0; i < scope.length; i++) {
            int start = Math.min(scope[i][0], scope[i][1]);
            int end = Math.max(scope[i][0], scope[i][1]);
            int workTime = times[i][0];
            int restTime = times[i][1];

            int r = workTime + restTime;

            for (int j = start; j <= end; j++) {
                int status = j % r;
                if (status > 0 && status <= workTime) {
                    answer = Math.min(answer, j);
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {

    }
}
