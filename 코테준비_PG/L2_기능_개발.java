import java.util.ArrayList;
import java.util.List;

public class L2_기능_개발 {
    public static int[] solution(int[] progresses, int[] speeds) {
        int[] remains = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            remains[i] = 100 - progresses[i];

            if(remains[i] % speeds[i] == 0) remains[i] /= speeds[i];
            else remains[i] = remains[i] / speeds[i] + 1;
        }

        List<Integer> answer = new ArrayList<>();
        int cntOfDay = 1, dayOfPre = remains[0];
        for (int i = 1; i < remains.length; i++) {
            if (dayOfPre >= remains[i]) {
                cntOfDay += 1;
            } else {
                answer.add(cntOfDay);
                cntOfDay = 1;
                dayOfPre = remains[i];
            }
        }
        answer.add(cntOfDay);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] progresses = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};

        int[] answer = solution(progresses, speeds);

        for(int a : answer) System.out.println(a);

    }
}
