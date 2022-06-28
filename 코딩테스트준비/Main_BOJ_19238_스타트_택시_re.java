import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_19238_스타트_택시_re {
    static class Passenger {
        int startX, startY, endX, endY, dist;

        public Passenger(int startX, int startY, int endX, int endY, int dist) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Passenger passenger = (Passenger) o;
            return startX == passenger.startX && startY == passenger.startY && endX == passenger.endX && endY == passenger.endY && dist == passenger.dist;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startX, startY, endX, endY, dist);
        }
    }
    static final int INF = 987654321;
    static int N, M, fuel, minDist;
    static int[][] map;
    static int[] taxi;
    static List<Passenger> passengerList;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static PriorityQueue<Passenger> pq = new PriorityQueue<>(new Comparator<Passenger>() {
        @Override
        public int compare(Passenger o1, Passenger o2) {
            if (o1.dist == o2.dist) {
                if (o1.startX == o2.startX) {
                    return Integer.compare(o1.startY, o2.startY);
                }
                return Integer.compare(o1.startX, o2.startX);
            }
            return Integer.compare(o1.dist, o2.dist);
        }
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        fuel = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        taxi = new int[]{x, y};

        passengerList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int sy = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int ex = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int ey = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            passengerList.add(new Passenger(sx, sy, ex, ey, 0));
        }

        while (M-- > 0) {
            updateDist();

            int dist = pq.peek().dist;
            if (dist > fuel || dist == INF) {
                fuel = -1;
                break;
            }

            fuel -= dist;

            int startX = pq.peek().startX;
            int startY = pq.peek().startY;
            int endX = pq.peek().endX;
            int endY = pq.peek().endY;

            Passenger p = new Passenger(startX, startY, endX, endY,0);
            passengerList.remove(p);

            minDist = INF;
            dist = getDist(startX, startY, endX, endY);

            if (fuel < dist || fuel == INF) {
                fuel = -1;
                break;
            }

            fuel += dist;
            taxi = new int[]{endX, endY};
        }

        System.out.println(fuel);
    }

    private static void updateDist() {
        pq.clear();
        minDist = INF;

        for (Passenger p : passengerList) {
            int dist = getDist(taxi[0], taxi[1], p.startX, p.startY);
            minDist = Math.min(minDist, dist);

            pq.offer(new Passenger(p.startX, p.startY, p.endX, p.endY, dist));
        }
    }

    private static int getDist(int x1, int y1, int x2, int y2) {
        boolean[][] isVisited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();

        isVisited[x1][y1] = true;
        q.offer(new int[]{x1, y1, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if (minDist < now[2]) break;
            if(now[0] == x2 && now[1] == y2) return now[2];

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(isVisited[nx][ny] || map[nx][ny] == 1) continue;

                isVisited[nx][ny] = true;
                q.offer(new int[]{nx, ny, now[2] + 1});
            }
        }
        return INF;
    }
}
