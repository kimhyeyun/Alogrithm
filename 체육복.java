import java.util.Arrays;

public class 체육복 {
    public static void main(String[] args) {

    }

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int student[] = new int[n + 1];

        Arrays.fill(student, 1);

        for (int i = 0; i < lost.length; i++) {
            student[lost[i]]--;
        }

        for (int j = 0; j < reserve.length; j++) {
            student[reserve[j]]++;
        }

        for (int i = 1; i < n + 1; i++) {
            if (student[i] == 0) {
                if (i - 1 > 0 && student[i - 1] == 2) {
                    student[i - 1]--;
                    student[i]++;
                } else if (i + 1 <= n && student[i + 1] == 2) {
                    student[i]++;
                    student[i + 1]--;
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            if (student[i] >= 1)
                answer++;
        }

        return answer;
    }

}
