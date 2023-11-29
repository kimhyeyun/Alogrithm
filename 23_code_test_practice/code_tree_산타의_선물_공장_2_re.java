import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class code_tree_산타의_선물_공장_2_re {

    public static int n, m;
    public static int[] prev, next;
    public static int[] head, tail;
    public static int[] countOfGifts;

    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 100) initFactory();
        }
    }

    private static void initFactory() {
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        prev = new int[m + 1];
        next = new int[m + 1];

        head = new int[n + 1];
        tail = new int[n + 1];
        countOfGifts = new int[n + 1];

        List<Integer>[] gifts = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            gifts[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            gifts[Integer.parseInt(st.nextToken())].add(i);
        }

        for (int i = 1; i <= n; i++) {
            if (gifts[i].size() == 0) continue;

            head[i] = gifts[i].get(0);
            tail[i] = gifts[i].get(gifts[i].size() - 1);

            countOfGifts[i] += 1;

            for (int j = 0; j < gifts[i].size() - 1; j++) {
                next[gifts[i].get(j)] = gifts[i].get(j + 1);
                prev[gifts[i].get(j + 1)] = gifts[i].get(j);
            }
        }
    }
}
