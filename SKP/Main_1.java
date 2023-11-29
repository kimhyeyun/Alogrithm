import java.util.Arrays;

public class Main_1 {
    public static int solution(int[] t, int m) {
        int answer = 0;

        Arrays.sort(t);
        for (int i = 0; i < m; i++) {
            answer += (t[i] + 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] t = {4, 2, 3, 1};
        System.out.println(solution(t, 2));
    }
}
