import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_7490_0_만들기 {
    static int N;
    static List<String> answer;
    static String[] op = {"+", "-", " "};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            answer = new ArrayList<>();

            DFS(1, "1");

            Collections.sort(answer);

            for (String ans : answer) {
                sb.append(ans).append("\n");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void DFS(int num, String s) {
        if (num == N) {
            String expr = s.replaceAll(" ", "");
            if(cal(expr)) answer.add(s);

            return;
        }

        for (int i = 0; i < 3; i++) {
            DFS(num + 1, s + op[i] + Integer.toString(num + 1));
        }
    }

    private static boolean cal(String expr) {
        StringTokenizer st = new StringTokenizer(expr, "-|+", true);
        int sum = Integer.parseInt(st.nextToken());

        while (st.hasMoreElements()) {
            String s = st.nextToken();
            if(s.equals("+")) sum += Integer.parseInt(st.nextToken());
            else sum -= Integer.parseInt(st.nextToken());
        }

        if(sum == 0) return true;
        return false;
    }
}
