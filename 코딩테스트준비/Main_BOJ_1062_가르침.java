import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1062_가르침 {
    static int N, K, ans = Integer.MIN_VALUE;
    static boolean[] isVisited;
    static String[] strs;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        isVisited = new boolean[26];

        isVisited['a' - 'a'] = true;
        isVisited['c' - 'a'] = true;
        isVisited['i' - 'a'] = true;
        isVisited['n' - 'a'] = true;
        isVisited['t' - 'a'] = true;

        strs = new String[N];

        if (K < 5) {
            System.out.println(0);
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            strs[i] = s.substring(4, s.length() - 4);
        }

        nums = new int[K - 5];
        combi(0, 0);

        System.out.println(ans);
    }

    private static void combi(int idx, int start) {
        if (idx == K - 5) {
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                boolean flag = true;
                for (int j = 0; j < strs[i].length(); j++) {
                    if (!isVisited[strs[i].charAt(j) - 'a']) {
                        flag = false;
                        break;
                    }
                }
                if(flag) cnt += 1;
            }
            ans = ans < cnt ? cnt : ans;
            return;
        }
        for (int i = start; i < 26; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                combi(idx + 1, i);
                isVisited[i] = false;
            }
        }

    }
}
