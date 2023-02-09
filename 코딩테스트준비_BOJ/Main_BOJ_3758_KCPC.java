import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_BOJ_3758_KCPC {
    static class Team implements Comparable<Team>{
        int idx, score, lastTime, count;
        int[] scores;

        public Team(int idx, int k) {
            this.idx = idx;
            this.score = 0;
            this.lastTime = -1;
            this.count = 0;
            this.scores = new int[k + 1];
        }

        public void sum() {
            for (int i = 0; i < scores.length; i++) {
                this.score += scores[i];
            }
        }

        @Override
        public int compareTo(Team o) {
            if (this.score == o.score) {
                if (this.count == o.count) {
                    return this.lastTime - o.lastTime;
                }
                return this.count - o.count;
            }
            return o.score - this.score;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());
            int myTeamId = Integer.parseInt(stringTokenizer.nextToken());
            int m = Integer.parseInt(stringTokenizer.nextToken());

            Team[] teams = new Team[n + 1];
            for (int i = 0; i <= n; i++) {
                teams[i] = new Team(i, k);
            }

            for (int i = 0; i < m; i++) {
                stringTokenizer = new StringTokenizer(br.readLine());

                int id = Integer.parseInt(stringTokenizer.nextToken());
                int num = Integer.parseInt(stringTokenizer.nextToken());
                int s = Integer.parseInt(stringTokenizer.nextToken());

                teams[id].scores[num] = Math.max(s, teams[id].scores[num]);
                teams[id].lastTime = i;
                teams[id].count += 1;
            }

            for (int i = 1; i <= n; i++) {
                teams[i].sum();
            }

            Arrays.sort(teams);
            for (int i = 0; i <= n; i++) {
                if (teams[i].idx == myTeamId) {
                    sb.append(i + 1).append("\n");
                }
            }
        }

        System.out.println(sb);

    }
}
