import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2512_예산 {
    /*
    *
    1. 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정한다.
    2. 모든 요청이 배정될 수 없는 경우에는 특정한 정수 상한액을 계산하여 그 이상인 예산요청에는 모두 상한액을 배정한다.
    *   상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정한다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int max = 0;
        int sum = 0;
        int[] budgets = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            max = Math.max(max, x);
            sum += x;

            budgets[i] = x;
        }

        int M = Integer.parseInt(br.readLine());

        if (sum <= M) {
            System.out.println(max);
        } else {
            int start = 1;
            int end = M;
            int answer = Integer.MIN_VALUE;

            while (start <= end) {
                int mid = (start + end) / 2;
                sum = 0;

                for (int i = 0; i < N; i++) {
                    if (budgets[i] > mid) {
                        sum += mid;
                    } else {
                        sum += budgets[i];
                    }
                }

                if (sum <= M) {
                    answer = Math.max(answer, mid);
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            System.out.println(answer);
        }
    }
}
