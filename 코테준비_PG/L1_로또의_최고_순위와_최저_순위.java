import java.util.Arrays;

public class L1_로또의_최고_순위와_최저_순위 {
    static int[] solution(int[] lottos, int[] win_nums) {
        Arrays.sort(lottos);
        Arrays.sort(win_nums);

        int zero = 0, same = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                zero += 1;
                continue;
            }
            for (int win : win_nums) {
                if (lotto == win) {
                    same += 1;
                    break;
                } else if (lotto < win) break;
            }
        }
        int max = rankingCheck(same + zero);
        int min = rankingCheck(same);

        return new int[]{max, min};
    }

    private static int rankingCheck(int cnt) {
        switch (cnt) {
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
        }
        return 6;
    }

    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};

        int[] answer = solution(lottos, win_nums);

        System.out.println(answer[0]+" " +answer[1]);
    }
}
