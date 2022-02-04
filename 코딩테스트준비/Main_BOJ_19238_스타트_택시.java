import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_19238_스타트_택시 {

    static class Passenger {
        int fromX;
        int fromY;
        int dist;
        int toX;
        int toY;

        public Passenger(int fromX, int fromY) {
            this.fromX = fromX;
            this.fromY = fromY;
        }

        public Passenger(int fromX, int fromY, int dist) {
            this(fromX, fromY);
            this.dist = dist;
        }

        public Passenger(int fromX, int fromY, int dist, int toX, int toY) {
            this(fromX, fromY, dist);
            this.toX = toX;
            this.toY = toY;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Passenger passenger = (Passenger) o;
            return fromX == passenger.fromX && fromY == passenger.fromY && toX == passenger.toX && toY == passenger.toY && dist == passenger.dist;
        }

        @Override
        public int hashCode() {
            return Objects.hash(fromX, fromY, toX, toY, dist);
        }

        @Override
        public String toString() {
            return "Passenger{" +
                    "fromX=" + fromX +
                    ", fromY=" + fromY +
                    ", dist=" + dist +
                    ", toX=" + toX +
                    ", toY=" + toY +
                    '}';
        }
    }

    static int N, M, minDist;
    static long fuel;
    static boolean[][] map;
    static final int INF = 1000;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static Passenger driver = new Passenger(0, 0);
    //    static HashMap<Passenger, Boolean> passengerList = new HashMap<>();
    static List<Passenger> passengerList = new ArrayList<>();
    static PriorityQueue<Passenger> passengersNearBy = new PriorityQueue<>((o1, o2) -> {
        if (o1.dist == o2.dist) {
            if (o1.fromX == o2.fromX) {
                return Integer.compare(o1.fromY, o2.fromY);
            }
            return Integer.compare(o1.fromX, o2.fromX);
        }
        return Integer.compare(o1.dist, o2.dist);
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        fuel = Integer.parseInt(stringTokenizer.nextToken());

        map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken()) == 1 ? true : false;
            }
        }

        stringTokenizer = new StringTokenizer(br.readLine());
        driver.fromX = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        driver.fromY = Integer.parseInt(stringTokenizer.nextToken()) - 1;

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int fromX = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int fromY = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int toX = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int toY = Integer.parseInt(stringTokenizer.nextToken()) - 1;

//            passengerList.put(new Passenger(fromX, fromY, 0, toX, toY), true);
            passengerList.add(new Passenger(fromX, fromY, 0, toX, toY));
        }
        System.out.println(passengerList.size());
        for (Passenger p : passengerList) {
            System.out.println(p.toString());
        }

        while (M-- > 0) {
            updatePassengerList();

            for (Passenger p : passengersNearBy) {
                System.out.println("u : " + p.toString());
            }

            int dist = passengersNearBy.peek().dist;
            if (dist > fuel || dist == INF) {
                fuel = -1;
                break;
            }

            System.out.println("PickUp - dist : " + dist + " fuel : " + fuel);
            fuel -= dist;

            int fromX = passengersNearBy.peek().fromX;
            int fromY = passengersNearBy.peek().fromY;
            int toX = passengersNearBy.peek().toX;
            int toY = passengersNearBy.peek().toY;

            Passenger p = new Passenger(fromX, fromY, 0, toX, toY);
            System.out.println("remove : " + p.toString());
            passengerList.remove(p);
            for (Passenger pa : passengerList) {
                System.out.println("list : " + pa.toString());
            }


            minDist = INF;
            dist = getDist(fromX, fromY, toX, toY);

            System.out.println("Drop - dist : " + dist + " fuel : " + fuel);
            if (dist > fuel || dist == INF) {
                fuel = -1;
                break;
            }

            fuel += dist;
            driver.fromX = toX;
            driver.fromY = toY;
        }

        System.out.println(String.valueOf(fuel));
    }

    private static void updatePassengerList() {
        passengersNearBy.clear();
        minDist = INF;

        for (Passenger p : passengerList) {
            int dist = getDist(driver.fromX, driver.fromY, p.fromX, p.fromY);
            minDist = Math.min(minDist, dist);
            passengersNearBy.offer(new Passenger(p.fromX, p.fromY, dist, p.toX, p.toY));
        }

    }

    private static int getDist(int fromX, int fromY, int toX, int toY) {
        boolean[][] isVisited = new boolean[N][N];
        isVisited[fromX][fromY] = true;
        Queue<Passenger> q = new LinkedList<>();
        q.offer(new Passenger(fromX, fromY, 0));

        while (!q.isEmpty()) {
            Passenger now = q.poll();
            int x = now.fromX;
            int y = now.fromY;
            int dist = now.dist;

            if(minDist < dist) break;
            if(x == toX && y == toY) return dist;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny || isVisited[nx][ny] || map[nx][ny]) continue;
                isVisited[nx][ny] = true;
                q.offer(new Passenger(nx, ny, dist + 1));
            }
        }
        return INF;
    }

}
