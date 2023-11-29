import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_12919_A와_B_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        System.out.println(DFS(S, T));
    }

    private static int DFS(String original, String target) {
        if (original.length() == target.length()) {
            if(original.equals(target)) return 1;
            return 0;
        }

        if (target.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(target.substring(1));
            if(DFS(original, sb.reverse().toString()) == 1) return 1;
        }

        if (target.charAt(target.length() - 1) == 'A') {
            if(DFS(original, target.substring(0, target.length() - 1)) == 1) return 1;
        }

        return 0;
    }
}
