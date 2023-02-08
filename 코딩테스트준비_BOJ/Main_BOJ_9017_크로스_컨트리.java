import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_9017_크로스_컨트리 {
    static class Score implements Comparable<Score>{
        int idx;
        List<Integer> scores;
        int sum;
        int five;
        boolean isRight;

        public Score(int idx) {
            this.idx = idx;
            scores = new ArrayList<>();
            this.sum = Integer.MAX_VALUE;
            this.five = Integer.MAX_VALUE;
            isRight = false;
        }

        @Override
        public int compareTo(Score o) {
            if (o.sum == this.sum) {
                return this.five - o.five;
            }
            return this.sum - o.sum;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Map<Integer, Integer> teams = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            teams = new HashMap<>();
            list = new ArrayList<>();
            Score[] scores = new Score[N + 1];
            for (int i = 0; i < N + 1; i++) {
                scores[i] = new Score(i);
            }

            for (int i = 0; i < N; i++) {
                int t = Integer.parseInt(st.nextToken());
                teams.put(t, teams.getOrDefault(t, 0) + 1);
                list.add(t);
            }

            int score = 1;
            for (int i = 0; i < list.size(); i++) {
                int teamSize = teams.get(list.get(i));
                int teamIdx = list.get(i);

                if (teamSize < 6) continue;
                else {
                    scores[teamIdx].isRight = true;
                    scores[teamIdx].scores.add(score);
                    score += 1;
                }
            }

            for (int i = 1; i < N + 1; i++) {
                if (scores[i].isRight) {
                    scores[i].sum = 0;

                    for (int j = 0; j < 4; j++) {
                        scores[i].sum += scores[i].scores.get(j);
                    }
                    scores[i].five = scores[i].scores.get(4);
                }
            }

            Arrays.sort(scores);

            System.out.println(scores[0].idx);
        }
    }
}
