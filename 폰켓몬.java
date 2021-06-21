import java.util.Arrays;

public class 폰켓몬 {
    public static void main(String[] args) {

    }

    public int solution(int[] nums) {
        int answer = 0;
        int cnt = 1;
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i])
                cnt++;
        }

        if (cnt > nums.length / 2) {
            answer = nums.length / 2;
        } else {
            answer = cnt;
        }

        return answer;
    }
}
