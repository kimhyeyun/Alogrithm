import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_14889_스타트와_링크 {
    static int N, ans = Integer.MAX_VALUE;
    static int[][] stats;
    static boolean[]isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        stats = new int[N + 1][N + 1];
        isVisited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                stats[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        makeTeam(1, 0);

        System.out.println(ans);
    }

    private static void makeTeam(int idx, int cnt) {
        if (cnt == N / 2) {
            List<Integer> start = new ArrayList<>();
            List<Integer> link = new ArrayList<>();

            for (int i = 1; i < N + 1; i++) {
                if(isVisited[i]) start.add(i);
                else link.add(i);
            }

            int l = 0, s = 0;
            for (int i = 0; i < start.size(); i++) {
                for (int j = i + 1; j < start.size(); j++) {
                    if (i == j) continue;
                    s += (stats[start.get(i)][start.get(j)] + stats[start.get(j)][start.get(i)]);
                    l += (stats[link.get(i)][link.get(j)] + stats[link.get(j)][link.get(i)]);
                }
            }

            ans = Math.min(ans, Math.abs(l - s));
        }
        for (int i = idx; i < N + 1; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                makeTeam(i, cnt + 1);
                isVisited[i] = false;
            }
        }
    }
}
