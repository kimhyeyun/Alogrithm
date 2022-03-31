import java.util.Arrays;

public class 로또의_최고_순위와_최저_순위 {
    static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int zero = 0, same = 0;

        Arrays.sort(lottos);
        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) zero += 1;
            else break;
        }

        for (int i = zero; i < lottos.length; i++) {
            for (int j = 0; j < win_nums.length; j++) {
                if(lottos[i] == win_nums[j]) same += 1;
            }
        }

        answer[0] = findRank(same + zero);
        answer[1] = findRank(same);

        return answer;
    }

    private static int findRank(int cnt) {
        switch (cnt){
            case 6:
                return 1;
            case 5:
                return 2;
            case 4:
                return 3;
            case 3:
                return 4;
            case 2:
                return 5;
            default:
                return 6;
        }
    }

    public static void main(String[] args) {

    }
}
