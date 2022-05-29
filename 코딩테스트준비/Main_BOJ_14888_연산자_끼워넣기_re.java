import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14888_연산자_끼워넣기_re {
    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] nums, op;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        op = new int[4];

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        DFS(1, nums[0]);

        System.out.println(max);
        System.out.println(min);
    }

    private static void DFS(int idx, int result) {
        if (idx == N) {
            max = max < result ? result : max;
            min = result < min ? result : min;
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) {
                op[i] -= 1;
                switch (i) {
                    case 0:
                        DFS(idx + 1, result + nums[idx]);
                        break;
                    case 1:
                        DFS(idx + 1, result - nums[idx]);
                        break;
                    case 2:
                        DFS(idx + 1, result * nums[idx]);
                        break;
                    case 3:
                        DFS(idx + 1, result / nums[idx]);
                        break;
                }
                op[i] += 1;
            }
        }
    }
}
