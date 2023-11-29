import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class code_tree_코드트리_채점기_re {
    public static class Problem implements Comparable<Problem>{
        int time, id, num;

        public Problem(int time, int id, int num) {
            this.time = time;
            this.id = id;
            this.num = num;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.id != o.id) return this.id - o.id;
            return this.time - o.time;
        }
    }
    public static int q, n, count, answer;
    public static PriorityQueue<Problem>[] problems;
    public static PriorityQueue<Integer> restJudge;
    public static Map<String, Integer> domainToIndex;
    public static Set<Integer>[] isReadyQ;
    public static int[] start, end, gap, judgingDomain;
    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();
    public static final int MAX_D = 301;
    public static final int MAX_N = 50001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        problems = new PriorityQueue[MAX_D];
        isReadyQ = new Set[MAX_D];

        start = new int[MAX_D];
        end = new int[MAX_D];
        gap = new int[MAX_D];

        judgingDomain = new int[MAX_N];

        for (int i = 0; i < MAX_D; i++) {
            problems[i] = new PriorityQueue<>();
            isReadyQ[i] = new TreeSet<>();
        }

        q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 100) init();
            else if (command == 200) requestGrade();
            else if (command == 300) tryGrade();
            else if (command == 400) finish();
            else check();
        }
        System.out.println(sb);
    }

    private static void check() {
        int time = Integer.parseInt(st.nextToken());
        sb.append(answer).append("\n");
    }

    private static void finish() {
        int time = Integer.parseInt(st.nextToken());
        int judgeIndex = Integer.parseInt(st.nextToken());

        if (judgingDomain[judgeIndex] == 0) return;

        restJudge.add(judgeIndex);
        int domainIndex = judgingDomain[judgeIndex];
        judgingDomain[judgeIndex] = 0;

        gap[domainIndex] = time - start[domainIndex];
        end[domainIndex] = start[domainIndex] + 3 * gap[domainIndex];
    }

    private static void tryGrade() {
        int time = Integer.parseInt(st.nextToken());

        if (restJudge.isEmpty()) return;

        int minDomain = 0;
        Problem minProblem = new Problem(0, Integer.MAX_VALUE, 0);

        for (int i = 1; i <= count; i++) {
            if (end[i] > time) continue;
            if (!problems[i].isEmpty()) {
                Problem cur = problems[i].peek();

                if (minProblem.id > cur.id || (minProblem.id == cur.id && minProblem.time > cur.time)) {
                    minProblem = cur;
                    minDomain = i;
                }
            }
        }

        if (minDomain > 0) {
            int judgeIndex = restJudge.poll();

            problems[minDomain].poll();
            start[minDomain] = time;
            end[minDomain] = Integer.MAX_VALUE;

            judgingDomain[judgeIndex] = minDomain;
            isReadyQ[minDomain].remove(minProblem.num);

            answer -= 1;
        }
    }

    private static void requestGrade() {
        int time = Integer.parseInt(st.nextToken());
        int id = Integer.parseInt(st.nextToken());
        String url = st.nextToken();

        String[] split = url.split("/");
        if (!domainToIndex.containsKey(split[0])) {
            count += 1;
            domainToIndex.put(split[0], count);
        }

        int domainIndex = domainToIndex.get(split[0]);
        if (isReadyQ[domainIndex].contains(Integer.parseInt(split[1]))) return;

        isReadyQ[domainIndex].add(Integer.parseInt(split[1]));

        Problem problem = new Problem(time, id, Integer.parseInt(split[1]));
        problems[domainIndex].add(problem);

        answer += 1;
    }

    private static void init() {
        n = Integer.parseInt(st.nextToken());
        String url = st.nextToken();

        restJudge = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            restJudge.add(i);
        }

        domainToIndex = new TreeMap<>();
        String[] split = url.split("/");

        if (!domainToIndex.containsKey(split[0])) {
            count += 1;
            domainToIndex.put(split[0], count);
        }

        int domainIndex = domainToIndex.get(split[0]);
        isReadyQ[domainIndex].add(Integer.parseInt(split[1]));

        Problem newProblem = new Problem(0, 1, Integer.parseInt(split[1]));
        problems[domainIndex].add(newProblem);

        answer += 1;
    }

}
