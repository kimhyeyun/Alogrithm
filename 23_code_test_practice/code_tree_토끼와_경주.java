import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class code_tree_토끼와_경주 {
   /* static class Rabbit implements Comparable<Rabbit> {
        int index, dist;
        int x, y;
        int count;

        public Rabbit(int index, int dist, int x, int y) {
            this.index = index;
            this.dist = dist;
            this.x = x;
            this.y = y;
            this.count = 0;
        }

        @Override
        public int compareTo(Rabbit o) {
            if (this.count == o.count) {
                if (this.x + this.y == o.x + o.y) {
                    if (this.x == o.x) {
                        if (this.y == o.y) return this.index - o.index;
                        return this.y - o.y;
                    }
                    return this.x - o.x;
                }
                return (this.x + this.y) - (o.x + o.y);
            }
            return this.count - o.count;
        }
    }

    static int q, n, m, p, k, s, l;
    static PriorityQueue<Rabbit> rabbits;
    static long[] score;
    static StringTokenizer st;
    static StringBuilder sb;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    static final int MAX_P = 10000001;

    public static boolean cmp(Rabbit a, Rabbit b) {
        if (a.x + a.y != b.x + b.y) return a.x + a.y < b.x + b.y;
        if (a.x != b.x) return a.x < b.x;
        if (a.y != b.y) return a.y < b.y;
        return a.index < b.index;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            if (command == 100) readyRace();
            else if (command == 200){
                k = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());
                while (k-- > 0) startRace();

                Rabbit bonus = new Rabbit(0, 0, 0, 0);
                for (Rabbit rabbit : rabbits) {
                    if (rabbit.count == 0) continue;
                    if (cmp(bonus, rabbit)) bonus = rabbit;
                }
                score[bonus.index] += s;
            } else if (command == 300) {
                p = Integer.parseInt(st.nextToken());
                l = Integer.parseInt(st.nextToken());

                for (Rabbit rabbit : rabbits) {
                    if (rabbit.index == p) rabbit.dist *= l;
                }
            } else {
                long max = 0;
                for (Rabbit rabbit : rabbits) {
                    if (score[rabbit.index] > max) max = score[rabbit.index];
                }
                sb.append(max).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void startRace() {
        List<int[]> pos = new ArrayList<>();
        Rabbit rabbit = rabbits.poll();
        rabbit.count += 1;

        pos.add(move(rabbit, 0));
        pos.add(move(rabbit, 1));
        pos.add(move(rabbit, 2));
        pos.add(move(rabbit, 3));


        pos.sort(((o1, o2) -> {
            if (o1[0] + o1[1] == o2[0] + o2[0]) {
                if (o1[0] == o2[0]) return o2[1] - o1[1];
                return o2[0] - o1[0];
            }
            return (o2[0] + o2[1]) - (o1[0] + o1[1]);
        }));

        rabbit.x = pos.get(0)[0];
        rabbit.y = pos.get(0)[1];

        long s = pos.get(0)[0] + pos.get(0)[1] + 2;

        for (Rabbit r : rabbits) {
            score[r.index] += s;
        }

        rabbits.add(rabbit);
    }

    private static int[] move(Rabbit rabbit, int dir) {
        int s = rabbit.dist;
        if (dir == 0 || dir == 3) s %= ((n - 1) * 2);
        else s %= ((m - 1) * 2);

        int nx = rabbit.x;
        int ny = rabbit.y;

        int d = dir;
        for (int i = 0; i < s; i++) {
            nx += dx[d];
            ny += dy[d];

            if (nx < 0 || ny < 0 || n <= nx || m <= ny) {
                nx -= dx[d] * 2;
                ny -= dy[d] * 2;

                if (d == 0) d = 3;
                else if (d == 1) d = 2;
                else if (d == 2) d = 1;
                else d = 0;
            }
        }
        return new int[]{nx, ny};
    }

    private static void readyRace() {
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        rabbits = new PriorityQueue<>();
        score = new long[MAX_P];

        for (int i = 0; i < p; i++) {
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            rabbits.add(new Rabbit(p, d, 0, 0));
        }

    }*/ // 시간 초과

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Rabbit implements Comparable<Rabbit> {
        int index;
        int x, y;
        int count;

        public Rabbit(int index, int count, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Rabbit o) {
            if (this.count == o.count) {
                if (this.x + this.y == o.x + o.y) {
                    if (this.x == o.x) {
                        if (this.y == o.y) return this.index - o.index;
                        return this.y - o.y;
                    }
                    return this.x - o.x;
                }
                return (this.x + this.y) - (o.x + o.y);
            }
            return this.count - o.count;
        }
    }


    public static int n, m, p;
    public static int[] id, distOfRabbit, countOfJump;
    public static long[] score;
    public static Pair[] pointOfRabbit;
    public static Map<Integer, Integer> idToIndex;
    public static boolean[] isRan;
    public static long totalScore;
    static StringTokenizer st;
    static StringBuilder sb;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};

    public static boolean cmp(Rabbit a, Rabbit b) {
        if (a.x + a.y != b.x + b.y) return a.x + a.y < b.x + b.y;
        if (a.x != b.x) return a.x < b.x;
        if (a.y != b.y) return a.y < b.y;
        return a.index < b.index;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command ==  100) init();
            else if (command == 200) startRace();
            else if (command == 300) distUp();
            else printResult();
        }

    }

    private static void printResult() {
        long answer = 0;
        for (int i = 1; i <= p; i++) {
            answer = Math.max(answer, score[i] + totalScore);
        }
        System.out.println(answer);
    }

    private static void distUp() {
        int id = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int index = idToIndex.get(id);
        distOfRabbit[index] *= t;
    }

    public static Rabbit getUpRabbit(Rabbit curRabbit, int dis) {
        Rabbit upRabbit = curRabbit;
        dis %= 2 * (n - 1);

        if(dis >= upRabbit.x - 1) {
            dis -= (upRabbit.x - 1);
            upRabbit.x = 1;
        }
        else {
            upRabbit.x -= dis;
            dis = 0;
        }

        if(dis >= n - upRabbit.x) {
            dis -= (n - upRabbit.x);
            upRabbit.x = n;
        }
        else {
            upRabbit.x += dis;
            dis = 0;
        }

        upRabbit.x -= dis;

        return upRabbit;
    }
    public static Rabbit getDownRabbit(Rabbit curRabbit, int dis) {
        Rabbit downRabbit = curRabbit;
        dis %= 2 * (n - 1);

        if(dis >= n - downRabbit.x) {
            dis -= (n - downRabbit.x);
            downRabbit.x = n;
        }
        else {
            downRabbit.x += dis;
            dis = 0;
        }

        if(dis >= downRabbit.x - 1) {
            dis -= (downRabbit.x - 1);
            downRabbit.x = 1;
        }
        else {
            downRabbit.x -= dis;
            dis = 0;
        }

        downRabbit.x += dis;

        return downRabbit;
    }
    public static Rabbit getLeftRabbit(Rabbit curRabbit, int dis) {
        Rabbit leftRabbit = curRabbit;
        dis %= 2 * (m - 1);

        if(dis >= leftRabbit.y - 1) {
            dis -= (leftRabbit.y - 1);
            leftRabbit.y = 1;
        }
        else {
            leftRabbit.y -= dis;
            dis = 0;
        }

        if(dis >= m - leftRabbit.y) {
            dis -= (m - leftRabbit.y);
            leftRabbit.y = m;
        }
        else {
            leftRabbit.y += dis;
            dis = 0;
        }

        leftRabbit.y -= dis;

        return leftRabbit;
    }

    public static Rabbit getRightRabbit(Rabbit curRabbit, int dis) {
        Rabbit rightRabbit = curRabbit;
        dis %= 2 * (m - 1);

        if(dis >= m - rightRabbit.y) {
            dis -= (m - rightRabbit.y);
            rightRabbit.y = m;
        }
        else {
            rightRabbit.y += dis;
            dis = 0;
        }

        if(dis >= rightRabbit.y - 1) {
            dis -= (rightRabbit.y - 1);
            rightRabbit.y = 1;
        }
        else {
            rightRabbit.y -= dis;
            dis = 0;
        }

        rightRabbit.y += dis;

        return rightRabbit;
    }
    private static void startRace() {
        int k = Integer.parseInt(st.nextToken());
        int bonus = Integer.parseInt(st.nextToken());
        PriorityQueue<Rabbit> rabbits = new PriorityQueue<>();

        isRan = new boolean[p + 1];

        for (int i = 1; i <= p; i++) {
            Rabbit rabbit = new Rabbit(id[i], countOfJump[i], pointOfRabbit[i].x, pointOfRabbit[i].y);
            rabbits.add(rabbit);
        }

        while (k-- > 0) {
            Rabbit cur = rabbits.poll();

            int dist = distOfRabbit[idToIndex.get(cur.index)];
            Rabbit next = new Rabbit(cur.index, cur.count, cur.x, cur.y);
            next.x = 0;
            next.y = 0;

//            List<Pair> pos = new ArrayList<>();
//
//            pos.add(move(cur, 0, dist));
//            pos.add(move(cur, 1, dist));
//            pos.add(move(cur, 2, dist));
//            pos.add(move(cur, 3, dist));
//
//            pos.sort(((o1, o2) -> {
//                if (o1.x + o1.y == o2.x + o2.y) {
//                    if (o1.x == o2.x) return o2.y - o1.y;
//                    return o2.x - o1.x;
//                }
//                return (o2.x + o2.y) - (o1.x + o1.y);
//            }));
//
//            next.x = pos.get(0).x;
//            next.y = pos.get(0).y;

            Rabbit up = getUpRabbit(new Rabbit(cur.index, cur.count, cur.x, cur.y), dist);
            if (cmp(next, up)) next = up;

            Rabbit down = getDownRabbit(new Rabbit(cur.index, cur.count, cur.x, cur.y), dist);
            if (cmp(next,down)) next = down;

            Rabbit left = getLeftRabbit(new Rabbit(cur.index, cur.count, cur.x, cur.y), dist);
            if (cmp(next, left)) next = left;

            Rabbit right = getRightRabbit(new Rabbit(cur.index, cur.count, cur.x, cur.y), dist);
            if (cmp(next, right)) next = right;

            next.count += 1;
            rabbits.add(next);

            int nextIndex = idToIndex.get(next.index);
            pointOfRabbit[nextIndex] = new Pair(next.x, next.y);
            countOfJump[nextIndex] += 1;

            isRan[nextIndex] = true;
            score[nextIndex] -= (next.x + next.y);
            totalScore += (next.x + next.y);
        }

        Rabbit bonusRabbit = new Rabbit(0, 0, 0, 0);
        while (!rabbits.isEmpty()) {
            Rabbit cur = rabbits.poll();

            if (!isRan[idToIndex.get(cur.index)]) continue;
            if (cmp(bonusRabbit, cur)) bonusRabbit = cur;
        }

        score[idToIndex.get(bonusRabbit.index)] += bonus;
    }

    private static Pair move(Rabbit rabbit, int dir, int dist) {
        int s = dist;
        if (dir == 0 || dir == 3) s %= ((n - 1) * 2);
        else s %= ((m - 1) * 2);

        int nx = rabbit.x;
        int ny = rabbit.y;

        int d = dir;
        for (int i = 0; i < s; i++) {
            nx += dx[d];
            ny += dy[d];

            if (nx <= 0 || ny <= 0 || n < nx || m < ny) {
                nx -= dx[d] * 2;
                ny -= dy[d] * 2;

                if (d == 0) d = 3;
                else if (d == 1) d = 2;
                else if (d == 2) d = 1;
                else d = 0;
            }
        }
        return new Pair(nx, ny);
    }

    private static void init() {
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        id = new int[p + 1];
        distOfRabbit = new int[p + 1];
        idToIndex = new HashMap<>();
        pointOfRabbit = new Pair[p + 1];
        countOfJump = new int[p + 1];
        score = new long[p + 1];

        for (int i = 1; i <= p; i++) {
            id[i] = Integer.parseInt(st.nextToken());
            distOfRabbit[i] = Integer.parseInt(st.nextToken());
            idToIndex.put(id[i], i);
            pointOfRabbit[i] = new Pair(1, 1);
        }


    }
}

