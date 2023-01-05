import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_12919_A와_B_2 {
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        DFS(S, T);

        System.out.println(answer);
    }

    private static void DFS(String original, String dest) {
        if (original.length() == dest.length()) {
            if (original.equals(dest)) {
                answer = 1;
            }
            return;
        }

        if (dest.charAt(0) == 'B') {
            String tmp = dest.substring(1);
            StringBuilder sb = new StringBuilder(tmp);
            DFS(original, sb.reverse().toString());
        }

        if (dest.charAt(dest.length() - 1) == 'A') {
            DFS(original, dest.substring(0, dest.length() - 1));
        }

        return;
    }
}
