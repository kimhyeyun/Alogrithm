import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14888_연산자_끼워넣기 {
    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] nums, operations;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        operations = new int[4];
        stringTokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operations[i] = Integer.parseInt(stringTokenizer.nextToken());
        }


        DFS(nums[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    private static void DFS(int num, int idx) {
        if (idx == N) {
            max = max < num ? num : max;
            min = num < min ? num : min;
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operations[i] > 0) {
                operations[i] -= 1;

                switch (i) {
                    case 0:
                        DFS(num + nums[idx], idx + 1);
                        break;
                    case 1:
                        DFS(num - nums[idx], idx + 1);
                        break;
                    case 2:
                        DFS(num * nums[idx], idx + 1);
                        break;
                    case 3:
                        DFS(num / nums[idx], idx + 1);
                        break;
                }

                operations[i] += 1;
            }
        }
    }
}
