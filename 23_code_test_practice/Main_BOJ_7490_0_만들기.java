import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_7490_0_만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            List<String> answer = new LinkedList<>();
            DFS(1, "1", answer, N);

            Collections.sort(answer);

            for (String a : answer) sb.append(a).append("\n");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static final String[] op = {"+", "-", " "};
    private static void DFS(int num, String str, List<String> answer, int N) {
        if (num == N) {
            String expr = str.replaceAll(" ", "");
            if(cal(expr)) answer.add(str);
            return;
        }

        for (int i = 0; i < 3; i++) {
            DFS(num + 1, str + op[i] + Integer.toString(num + 1), answer, N);
        }
    }

    private static boolean cal(String expr) {
        StringTokenizer st = new StringTokenizer(expr, "-|+", true);
        int sum = Integer.parseInt(st.nextToken());

        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if(s.equals("+")) sum += Integer.parseInt(st.nextToken());
            else sum -= Integer.parseInt(st.nextToken());
        }

        return sum == 0;
    }
}
