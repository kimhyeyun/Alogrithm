import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n_19238_스타트_택시 {
    static class Guest {
        int startX, startY, endX, endY, dist;

        public Guest(int startX, int startY, int endX, int endY, int dist) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.dist = dist;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startX, startY, endX, endY, dist);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            Guest passenger = (Guest) obj;
            return startX == passenger.startX && startY == passenger.startY && endX == passenger.endX && endY == passenger.endY && dist == passenger.dist;
        }
    }
    static int N, M, fuel, minDist;
    static int[][] map;
    static int[] taxi;
    static List<Guest> guests;
    static PriorityQueue<Guest> pq = new PriorityQueue<>(new Comparator<Guest>() {
        @Override
        public int compare(Guest o1, Guest o2) {
            if (o1.dist == o2.dist) {
                if (o1.startX == o2.startX) {
                    return Integer.compare(o1.startY, o2.startY);
                }
                return Integer.compare(o1.startX, o2.startX);
            }
            return Integer.compare(o1.dist, o2.dist);
        }
    });
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        taxi = new int[]{x, y};

        guests = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;

            guests.add(new Guest(sx, sy, ex, ey, 0));
        }

        while (M-- > 0) {
            getDistByGuests();

            int dist = pq.peek().dist;
            if (dist > fuel || dist == Integer.MAX_VALUE) {
                fuel = -1;
                break;
            }

            fuel -= dist;

            int startX = pq.peek().startX;
            int startY = pq.peek().startY;
            int endX = pq.peek().endX;
            int endY = pq.peek().endY;

            Guest g = new Guest(startX, startY, endX, endY, 0);
            guests.remove(g);

            minDist = Integer.MAX_VALUE;
            dist = getDist(new int[]{startX, startY}, new int[]{endX, endY});

            if (fuel < dist || dist == Integer.MAX_VALUE) {
                fuel = -1;
                break;
            }

            fuel += dist;
            taxi = new int[]{endX, endY};
        }

        System.out.println(fuel);
    }

    private static void getDistByGuests() {
        pq.clear();
        minDist = Integer.MAX_VALUE;

        for (Guest g : guests) {
            int dist = getDist(taxi, new int[]{g.startX, g.startY});
            minDist = Math.min(minDist, dist);

            pq.offer(new Guest(g.startX, g.startY, g.endX, g.endY, dist));
        }

    }

    private static int getDist(int[] start, int[] end) {
        boolean[][] isVisited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();

        isVisited[start[0]][start[1]] = true;
        q.offer(new int[]{start[0], start[1], 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if(minDist < now[2]) break;
            if(now[0] == end[0] && now[1] == end[1]) return now[2];

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(isVisited[nx][ny] || map[nx][ny] == 1) continue;

                isVisited[nx][ny] = true;
                q.offer(new int[]{nx, ny, now[2] + 1});
            }
        }
        return Integer.MAX_VALUE;
    }
}
