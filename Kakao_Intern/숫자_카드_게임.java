import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 숫자_카드_게임 {

    static int[] num;
    static boolean[] isChoose;
    static int N, K, ans = 0;
    static final int mod = 1000000007;
    static Set<List> cards = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        num = new int[N];
        isChoose = new boolean[N];
        for (int i = 0; i < N; i++) {
            num[i] = i;
        }

        DFS(0, 0, 0);

        System.out.println(ans % mod);
    }

    private static void DFS(int cnt, int sum, int idx) {
        if (cnt == K) {
            if (sum % N == 0) {
                ans += 1;
            }

            return;
        }

        for (int i = idx; i < N; i++) {
            if (!isChoose[i]) {
                isChoose[i] = true;
                DFS(cnt + 1, sum + num[i], i + 1);
                isChoose[i] = false;
            }
        }
    }

}
