import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class code_tree_토끼와_경주_re {
    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static class Rabbit implements Comparable<Rabbit> {
        int id;
        int x, y;
        int count;

        public Rabbit(int id, int x, int y, int count) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Rabbit o) {
            if (this.count != o.count) return this.count - o.count;
            if (this.x + this.y != o.x + o.y) return (this.x + this.y) - (o.x + o.y);
            if (this.x != o.x) return this.x - o.x;
            if (this.y != o.y) return this.y - o.y;
            return this.id - o.id;
        }
    }

    public static int q, n, m, p;
    public static int[] idOfRabbits, distOfRabbits, countOfJump;
    public static Pos[] posOfRabbits;
    public static long[] scoreOfRabbits;
    public static long totalOfScores;
    public static boolean[] isRan;
    public static Map<Integer, Integer> indexOfIds;

    public static BufferedReader br;
    public static StringTokenizer st;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        q = Integer.parseInt(st.nextToken());

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 100) readyRace();
            else if (command == 200) startRace();
            else if (command == 300) changeDist();
            else bestRabbit();
        }
        System.out.println(sb);
    }

    private static void bestRabbit() {
        long answer = 0;
        for (int i = 0; i < p; i++) {
            answer = Math.max(answer, scoreOfRabbits[i] + totalOfScores);
        }
        sb.append(answer).append("\n");
    }

    private static void changeDist() {
        int id = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int index = indexOfIds.get(id);
        distOfRabbits[index] *= l;
    }

    private static void startRace() {
        int k = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        PriorityQueue<Rabbit> rabbits = new PriorityQueue<>();
        isRan = new boolean[p];

        for (int i = 0; i < p; i++) {
            Rabbit rabbit = new Rabbit(idOfRabbits[i], posOfRabbits[i].x, posOfRabbits[i].y, countOfJump[i]);
            rabbits.add(rabbit);
        }

        while (k-- > 0) {
            Rabbit cur = rabbits.poll();

            int dist = distOfRabbits[indexOfIds.get(cur.id)];
            Rabbit next = new Rabbit(cur.id, cur.x, cur.y, cur.count);
            next.x = 0;
            next.y = 0;

            Rabbit up = getUpRabbit(new Rabbit(cur.id, cur.x, cur.y, cur.count), dist);
            if (cmp(next, up)) next = up;

            Rabbit down = geDownRabbit(new Rabbit(cur.id, cur.x, cur.y, cur.count), dist);
            if (cmp(next, down)) next = down;

            Rabbit right = getRightRabbit(new Rabbit(cur.id, cur.x, cur.y, cur.count), dist);
            if (cmp(next, right)) next = right;

            Rabbit left = getLeftRabbit(new Rabbit(cur.id, cur.x, cur.y, cur.count), dist);
            if (cmp(next, left)) next = left;

            next.count += 1;
            rabbits.add(next);

            int nextIndex = indexOfIds.get(next.id);
            posOfRabbits[nextIndex] = new Pos(next.x, next.y);
            countOfJump[nextIndex] += 1;

            isRan[nextIndex] = true;
            scoreOfRabbits[nextIndex] -= (next.x + next.y);
            totalOfScores += (next.x + next.y);
        }

        Rabbit bonus = new Rabbit(0, 0, 0, 0);
        while (!rabbits.isEmpty()) {
            Rabbit cur = rabbits.poll();

            if (!isRan[indexOfIds.get(cur.id)]) continue;
            if (cmp(bonus, cur)) bonus = cur;
        }

        scoreOfRabbits[indexOfIds.get(bonus.id)] += s;
    }

    private static Rabbit getLeftRabbit(Rabbit rabbit, int dist) {
        dist %= 2 * (m - 1);

        if (dist >= (rabbit.y - 1)) {
            dist -= (rabbit.y - 1);
            rabbit.y = 1;
        } else {
            rabbit.y -= dist;
            dist = 0;
        }

        if (dist >= (m - rabbit.y)) {
            dist -= (m - rabbit.y);
            rabbit.y = m;
        } else {
            rabbit.y += dist;
            dist = 0;
        }

        rabbit.y -= dist;
        return rabbit;
    }

    private static Rabbit getRightRabbit(Rabbit rabbit, int dist) {
        dist %= 2 * (m - 1);

        if (dist >= m - rabbit.y) {
            dist -= (m - rabbit.y);
            rabbit.y = m;
        } else {
            rabbit.y += dist;
            dist = 0;
        }

        if (dist >= rabbit.y - 1) {
            dist -= (rabbit.y - 1);
            rabbit.y = 1;
        } else {
            rabbit.y -= dist;
            dist = 0;
        }

        rabbit.y += dist;
        return rabbit;
    }

    private static Rabbit geDownRabbit(Rabbit rabbit, int dist) {
        dist %= 2 * (n - 1);

        if (dist >= n - rabbit.x) {
            dist -= (n - rabbit.x);
            rabbit.x = n;
        } else {
            rabbit.x += dist;
            dist = 0;
        }

        if (dist >= rabbit.x - 1) {
            dist -= (rabbit.x - 1);
            rabbit.x = 1;
        } else {
            rabbit.x -= dist;
            dist = 0;
        }

        rabbit.x += dist;

        return rabbit;
    }

    private static boolean cmp(Rabbit a, Rabbit b) {
        if (a.x + a.y != b.x + b.y) return (a.x + a.y) < (b.x + b.y);
        if (a.x != b.x) return a.x < b.x;
        if (a.y != b.y) return a.y < b.y;
        return a.id < b.id;
    }

    private static Rabbit getUpRabbit(Rabbit rabbit, int dist) {
        dist %= 2 * (n - 1);

        if (dist >= rabbit.x - 1) {
            dist -= rabbit.x - 1;
            rabbit.x = 1;
        } else {
            rabbit.x -= dist;
            dist = 0;
        }

        if (dist >= n - rabbit.x) {
            dist -= (n - rabbit.x);
            rabbit.x = n;
        } else {
            rabbit.x += dist;
            dist = 0;
        }

        rabbit.x -= dist;
        return rabbit;
    }

    private static void readyRace() {
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        idOfRabbits = new int[p];
        distOfRabbits = new int[p];
        countOfJump = new int[p];

        scoreOfRabbits = new long[p];
        posOfRabbits = new Pos[p];

        indexOfIds = new HashMap<>();

        for (int i = 0; i < p; i++) {
            idOfRabbits[i] = Integer.parseInt(st.nextToken());
            distOfRabbits[i] = Integer.parseInt(st.nextToken());
            indexOfIds.put(idOfRabbits[i], i);
            posOfRabbits[i] = new Pos(1, 1);
        }
    }

}
