import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_1781_컵라면 {
    static class Problem implements Comparable<Problem>{
        int idx;
        int deadline;
        int ramen;

        public Problem(int idx, int deadline, int ramen) {
            this.idx = idx;
            this.deadline = deadline;
            this.ramen = ramen;
        }

        @Override
        public int compareTo(Problem o) {
            return this.deadline - o.deadline;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int maxDeadLine = 0;

        PriorityQueue<Problem> problems = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            problems.add(new Problem(i, d, r));
        }

        PriorityQueue<Integer> result = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            Problem p = problems.poll();
            result.add(p.ramen);

            if (p.deadline < result.size()) {
                result.poll();
            }

        }

        int answer = 0;
        while (!result.isEmpty()) {
            answer += result.poll();
        }

        System.out.println(answer);

    }
}
