public class L1_소수_만들기 {
    static boolean[] isVisited;
    static int answer = 0;
    public static int solution(int[] nums) {
        isVisited = new boolean[nums.length];
        DFS(0, 0,0, nums);

        return answer;
    }

    private static void DFS(int cnt, int sum, int idx, int[] nums) {
        if (cnt == 3) {
            boolean flag = false;
            for (int i = 2; i <= Math.sqrt(sum); i++) {
                if (sum % i == 0) {
                    flag = true;
                    break;
                }
            }

            if(!flag) answer += 1;
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                DFS(cnt + 1, sum + nums[i], i + 1, nums);
                isVisited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(solution(nums));

    }
}
