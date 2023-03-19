import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14889_스타트와_링크 {
    static int N, answer;
    static int[][] stats;
    static int[] teamLink, teamStart;
    static boolean[] isPicked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        stats = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                stats[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        teamLink = new int[N / 2];
        teamStart = new int[N / 2];
        isPicked = new boolean[N + 1];

        answer = Integer.MAX_VALUE;

        DFS(0, 1);

        System.out.println(answer);
    }

    private static void DFS(int cnt, int index) {
        if (cnt == N / 2) {
            int idx = 0;
            for (int i = 1; i <= N; i++) {
                if(!isPicked[i]){
                    teamStart[idx++] = i;
                }
            }
            answer = Math.min(answer, calDiffStats());
            return;
        }

        for (int i = index; i <= N; i++) {
            if(isPicked[i]) continue;

            isPicked[i] = true;
            teamLink[cnt] = i;
            DFS(cnt + 1, i + 1);
            isPicked[i] = false;
        }
    }

    private static int calDiffStats() {
        int sumOfLink = 0, sumOfStart = 0;

        for (int i = 0; i < teamStart.length - 1; i++) {
            int s1 = teamStart[i];
            int l1 = teamLink[i];
            for (int j = i + 1; j < teamStart.length; j++) {
                int s2 = teamStart[j];
                int l2 = teamLink[j];

                sumOfStart += (stats[s1][s2] + stats[s2][s1]);
                sumOfLink += (stats[l1][l2] + stats[l2][l1]);
            }
        }

        return Math.abs(sumOfLink - sumOfStart);
    }
}
