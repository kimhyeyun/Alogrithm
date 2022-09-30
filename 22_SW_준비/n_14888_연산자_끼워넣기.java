import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_14888_연산자_끼워넣기 {
    static int N, maxAnswer, minAnswer;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        maxAnswer = Integer.MIN_VALUE;
        minAnswer = Integer.MAX_VALUE;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        int[] operators = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        solution(1, numbers[0], operators);

        System.out.println(maxAnswer);
        System.out.println(minAnswer);
    }

    private static void solution(int idx, int sum, int[] operators) {
        if (idx == N) {
            minAnswer = Math.min(sum, minAnswer);
            maxAnswer = Math.max(sum, maxAnswer);
            return;
        }

        if (operators[0] > 0) {
            operators[0] -= 1;
            solution(idx + 1, sum + numbers[idx], operators);
            operators[0] += 1;
        }
        if (operators[1] > 0) {
            operators[1] -= 1;
            solution(idx + 1, sum - numbers[idx], operators);
            operators[1] += 1;
        }
        if (operators[2] > 0) {
            operators[2] -= 1;
            solution(idx + 1, sum * numbers[idx], operators);
            operators[2] += 1;
        }
        if (operators[3] > 0) {
            operators[3] -= 1;
            solution(idx + 1, sum / numbers[idx], operators);
            operators[3] += 1;
        }

    }


}
