import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_BOJ_16339_괄호_추가하기_3 {
    static int N;
    static int[][] min, max;
    static char[] expr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        expr = br.readLine().toCharArray();

        min = new int[N][N];
        max = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(min[i], Integer.MAX_VALUE);
            Arrays.fill(max[i], Integer.MIN_VALUE);
        }

        for (int i = 0; i < N; i += 2) {
            min[i][i] = expr[i] - '0';
            max[i][i] = expr[i] - '0';
        }

        for (int j = 2; j < N; j += 2) {
            for (int i = 0; i < N - j; i += 2) {
                for (int k = 2; k <= j; k += 2) {
                    char op = expr[i + k - 1];
                    int[] tmp = new int[4];

                    tmp[0] = calculate(max[i][i + k - 2], max[i + k][i + j], op);
                    tmp[1] = calculate(max[i][i + k - 2], min[i + k][i + j], op);
                    tmp[2] = calculate(min[i][i + k - 2], max[i + k][i + j], op);
                    tmp[3] = calculate(min[i][i + k - 2], min[i + k][i + j], op);

                    Arrays.sort(tmp);
                    max[i][i + j] = Math.max(max[i][i + j], tmp[3]);
                    min[i][i + j] = Math.min(min[i][i + j], tmp[0]);
                }
            }
        }
        System.out.println(max[0][N - 1]);
    }

    private static int calculate(int operand1, int operand2, char operator) {
        return switch (operator) {
            case '+' -> operand1 + operand2;
            case '-' -> operand1 - operand2;
            case '*' -> operand1 * operand2;
            case '/' -> operand1 / operand2;
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }
}
