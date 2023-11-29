import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_3758_KCPC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Team[] teams = new Team[n + 1];
            for (int i = 0; i <= n; i++) {
                teams[i] = new Team(i, k);
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int id = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int score = Integer.parseInt(st.nextToken());

                teams[id].scores[num] = Math.max(teams[id].scores[num], score);
                teams[id].addCount();
                teams[id].log = i;
            }

            for (int i = 1; i <= n; i++) {
                teams[i].addScore();
            }

            Arrays.sort(teams);
            for (int i = 0; i <= n; i++) {
                if(teams[i].id == t){
                    sb.append(i + 1).append("\n");
                    break;
                }
            }
        }

        System.out.println(sb);
    }

    private static class Team implements Comparable<Team> {
        int id, score;
        int[] scores;
        int count, log;

        public Team(int id, int k) {
            this.id = id;
            this.score = 0;
            this.count = 0;
            this.log = -1;
            this.scores = new int[k + 1];
        }

        public void addScore() {
            for (int i = 1; i < scores.length; i++) {
                this.score += this.scores[i];
            }
        }

        public void addCount() {
            this.count += 1;
        }

        @Override
        public int compareTo(Team o) {
            if (this.score == o.score) {
                if (this.count == o.count) {
                    return this.log - o.log;
                }
                return this.count - o.count;
            }
            return o.score - this.score;
        }
    }
}
