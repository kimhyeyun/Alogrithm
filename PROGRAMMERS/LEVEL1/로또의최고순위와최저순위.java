import java.util.Arrays;

public class 로또의최고순위와최저순위 {
    public static void main(String[] args) {

    }

    public int[] solution(int[] lottos, int[] win_nums) {
        int answer[] = new int[2];
        int zero = 0;
        int idx = 0;
        int cnt = 0;

        Arrays.sort(lottos);
        Arrays.sort(win_nums);

        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) {
                zero++;
                idx = i;
            } else
                break;
        }

        for (int i = 0; i < win_nums.length; i++) {
            for (int j = idx; j < lottos.length; j++) {
                if (lottos[j] == win_nums[i]) {
                    cnt++;
                }
            }
        }

        switch (cnt + zero) {
            case 2:
                answer[0] = 5;
                break;
            case 3:
                answer[0] = 4;
                break;
            case 4:
                answer[0] = 3;
                break;
            case 5:
                answer[0] = 2;
                break;
            case 6:
                answer[0] = 1;
                break;
            default:
                answer[0] = 6;
                break;
        }

        switch (cnt) {
            case 2:
                answer[1] = 5;
                break;
            case 3:
                answer[1] = 4;
                break;
            case 4:
                answer[1] = 3;
                break;
            case 5:
                answer[1] = 2;
                break;
            case 6:
                answer[1] = 1;
                break;
            default:
                answer[1] = 6;
                break;
        }

        return answer;
    }
}
