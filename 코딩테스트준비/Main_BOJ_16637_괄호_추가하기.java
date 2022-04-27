import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_16637_괄호_추가하기 {
    static int N;
    static String input;
    static Long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = br.readLine();
        ans = Long.MIN_VALUE;

        DFS(0, 0);

        System.out.println(ans);

    }

    private static void DFS(int idx, long sum) {
        if (idx >= N) {
            ans = Math.max(sum, ans);
            return;
        }

        char op = idx == 0 ? '+' : input.charAt(idx - 1);
        if (idx + 2 < N) {
            long braket = cal(input.charAt(idx) - '0', input.charAt(idx + 2) - '0', input.charAt(idx + 1));
            DFS(idx + 4, cal(sum, braket, op));
        }

        DFS(idx + 2, cal(sum, input.charAt(idx) - '0', op));
    }

    private static long cal(long i, long j, char op) {
        if(op == '+') return i + j;
        else if(op == '-') return i - j;
        return i * j;
    }
}
