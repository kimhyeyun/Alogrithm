import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_19238_스타트_택시 {
    static class Passenger{
        int index;
        int startX, startY;
        int endX, endY;
        int dist;

        public Passenger(int index, int startX, int startY, int endX, int endY, int dist) {
            this.index = index;
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.dist = dist;
        }
    }
    static int N, M, fuel;
    static int minDist;
    static int[][] map;
    static Passenger[] passengers;
    static int[] taxi;
    static PriorityQueue<Passenger> pq;

    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};

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
        taxi = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};

        passengers = new Passenger[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;

            passengers[i] = new Passenger(i, sx, sy, ex, ey, 0);
        }

        while (M-- > 0) {
            getDistByPassenger();

            Passenger p = pq.peek();
            int dist = p.dist;

            if (dist > fuel || dist == Integer.MAX_VALUE) {
                fuel = -1;
                break;
            }

            fuel -= dist;

            minDist = Integer.MAX_VALUE;
            dist = getDist(new int[]{p.startX, p.startY}, new int[]{p.endX, p.endY});

            if (dist > fuel || dist == Integer.MAX_VALUE) {
                fuel = -1;
                break;
            }

            fuel += dist;
            taxi = new int[]{p.endX, p.endY};

            int index = p.index;
            passengers[index] = null;
        }
        System.out.println(fuel);
    }

    private static void getDistByPassenger() {
        pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.dist == o2.dist) {
                if (o1.startX == o2.startX) {
                    return Integer.compare(o1.startY, o2.startY);
                }
                return Integer.compare(o1.startX, o2.startX);
            }
            return Integer.compare(o1.dist, o2.dist);
        });

        minDist = Integer.MAX_VALUE;

        for (int i = 0; i < passengers.length; i++) {
            if(passengers[i] == null) continue;

            int dist = getDist(taxi, new int[]{passengers[i].startX, passengers[i].startY});
            minDist = Math.min(minDist, dist);

            pq.offer(new Passenger(i, passengers[i].startX, passengers[i].startY, passengers[i].endX, passengers[i].endY, dist));
        }
    }

    private static int getDist(int[] start, int[] end) {
        boolean[][] isVisited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();

        isVisited[start[0]][start[1]] = true;
        q.add(new int[]{start[0], start[1], 0});

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
                q.add(new int[]{nx, ny, now[2] + 1});
            }
        }
        return Integer.MAX_VALUE;
    }
}

