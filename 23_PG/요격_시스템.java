import java.util.Arrays;

public class 요격_시스템 {

    public static int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, ((o1, o2) -> {
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        }));

        answer += 1;
        int end = targets[0][1];

        for (int i = 1; i < targets.length; i++) {
            if (targets[i][0] >= end) {
                end = targets[i][1];
                answer += 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}}));
    }
}
