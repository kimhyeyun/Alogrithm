import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class code_tree_코드트리_채점기 {
    public static class Task implements Comparable<Task> {
        int time, id, num;

        public Task(int time, int id, int num) {
            this.time = time;
            this.id = id;
            this.num = num;
        }

        @Override
        public int compareTo(Task o) {
            if (this.id == o.id) return this.time - o.time;
            return this.id - o.id;
        }
    }

    public static StringTokenizer st;
    public static StringBuilder sb;
    public static int q, n;
    public static int count, answer;
    public static int[] judgingDomain;
    public static TreeSet<Integer>[] isInReadyQ;
    public static PriorityQueue<Integer> restJudges;
    public static int[] start, gap, end;
    public static PriorityQueue<Task>[] tasks;
    public static TreeMap<String, Integer> domainToIndex;
    public static final int MAX_D = 300;
    public static final int MAX_N = 50000;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        q = Integer.parseInt(br.readLine());

        tasks = new PriorityQueue[MAX_D + 1];
        isInReadyQ = new TreeSet[MAX_D + 1];
        restJudges = new PriorityQueue<>();
        domainToIndex = new TreeMap<>();

        start = new int[MAX_D + 1];
        end = new int[MAX_D + 1];
        gap = new int[MAX_D + 1];

        judgingDomain = new int[MAX_N + 1];

        for (int i = 1; i <= MAX_D; i++) {
            tasks[i] = new PriorityQueue<>();
        }

        for (int i = 1; i <= MAX_D; i++) {
            isInReadyQ[i] = new TreeSet<>();
        }

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());

            if (command == 100) init();
            else if (command == 200) newTask();
            else if (command == 300) assign();
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
        int id = Integer.parseInt(st.nextToken());

        if (judgingDomain[id] == 0) return;

        restJudges.add(id);
        int domainIndex = judgingDomain[id];
        judgingDomain[id] = 0;

        gap[domainIndex] = time - start[domainIndex];
        end[domainIndex] = start[domainIndex] + 3 * gap[domainIndex];
    }

    private static void assign() {
        int time = Integer.parseInt(st.nextToken());

        if (restJudges.isEmpty()) return;

        int minDomain = 0;
        Task minTask = new Task(0, Integer.MAX_VALUE, 0);

        for (int i = 1; i <= count; i++) {
            if (end[i] > time) continue;
            if (!tasks[i].isEmpty()) {
                Task cur = tasks[i].peek();

                if (minTask.id > cur.id || (minTask.id == cur.id && minTask.time > cur.time)) {
                    minTask = cur;
                    minDomain = i;
                }
            }
        }

        if (minDomain > 0) {
            int judgeIndex = restJudges.poll();

            tasks[minDomain].poll();

            start[minDomain] = time;
            end[minDomain] = Integer.MAX_VALUE;

            judgingDomain[judgeIndex] = minDomain;
            isInReadyQ[minDomain].remove(minTask.num);

            answer -= 1;
        }
    }

    private static void newTask() {
        int time = Integer.parseInt(st.nextToken());
        int id = Integer.parseInt(st.nextToken());
        String url = st.nextToken();

        String[] split = url.split("/");
        String domain = split[0];
        int num = Integer.parseInt(split[1]);

        if (!domainToIndex.containsKey(domain)) {
            count += 1;
            domainToIndex.put(domain, count);
        }

        int domainIndex = domainToIndex.get(domain);

        if (isInReadyQ[domainIndex].contains(num)) return;

        isInReadyQ[domainIndex].add(num);

        Task newTask = new Task(time, id, num);
        tasks[domainIndex].add(newTask);

        answer += 1;
    }

    private static void init() {
        n = Integer.parseInt(st.nextToken());
        String url = st.nextToken();

        for (int i = 1; i <= n; i++) {
            restJudges.add(i);
        }

        String[] split = url.split("/");
        String domain = split[0];
        int num = Integer.parseInt(split[1]);

        if (!domainToIndex.containsKey(domain)) {
            count += 1;
            domainToIndex.put(domain, count);
        }

        int domainIndex = domainToIndex.get(domain);

        isInReadyQ[domainIndex].add(num);

        Task newTask = new Task(0, 1, num);
        tasks[domainIndex].add(newTask);

        answer += 1;
    }
}
