import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_25757_임스와_함께하는_미니게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        String game = st.nextToken();
        Set<String> nicknames = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String id = br.readLine();

            nicknames.add(id);
        }

        if (game.equals("Y")) {
            System.out.println(nicknames.size());
        } else if (game.equals("F")) {
            System.out.println(nicknames.size() / 2);
        } else {
            System.out.println(nicknames.size() / 3);
        }
    }
}
