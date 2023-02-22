import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_5852_Island_Travels {
    static class Position implements Comparable<Position>{
        int x, y;
        int cnt;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Position o) {
            return this.cnt - o.cnt;
        }
    }

    static int R, C;
    static int max, answer;
    static char[][] map;
    static int[][] num, road;
    static int[][] dp;
    static Position first;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        num = new int[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int cnt = 1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (num[i][j] == 0 && map[i][j] == 'X') {
                    setIsLandNumber(i, j, cnt++);
                }
            }
        }

        road = new int[cnt][cnt];
        for (int i = 1; i < cnt; i++) {
            for (int j = 1; j < cnt; j++) {
                road[i][j] = Integer.MAX_VALUE;
                if(i == j) road[i][j] = 0;
            }
        }

        int start = 1;
        boolean[] isVisited = new boolean[cnt];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X' && !isVisited[num[i][j]]) {
                    isVisited[num[i][j]] = true;
                    makeRoad(i, j, num[i][j]);
                }
            }
        }

        for (int i = 1; i < cnt; i++) {
            max = (max | (1 << i));
        }

        dp = new int[1 << cnt][cnt];
        answer = swim(0, 0, cnt);

        System.out.println(answer);
    }

    private static int swim(int visit, int idx, int cnt) {
        if(dp[visit][idx] != 0) return dp[visit][idx];
        if(max == visit) return dp[visit][idx] = 0;

        int ret = Integer.MAX_VALUE;
        for (int next = 1; next < cnt; next++) {
            if(idx == next || (visit & (1 << next)) != 0) continue;
            ret = Math.min(ret, swim((visit | (1 << next)), next, cnt) + road[idx][next]);
        }
        return dp[visit][idx] = ret;
    }

    private static void makeRoad(int x, int y, int number) {
        int[][] check = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(check[i], Integer.MAX_VALUE);
        }

        boolean[][] isVisited = new boolean[R][C];
        PriorityQueue<Position> pq = new PriorityQueue<>();

        pq.offer(new Position(x, y));
        isVisited[x][y] = true;
        check[x][y] = 0;

        while (!pq.isEmpty()) {
            Position now = pq.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                int nc = now.cnt;

                if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;
                if(isVisited[nx][ny] || map[nx][ny] == '.') continue;
                if(map[nx][ny] == 'S') nc += 1;

                if(check[nx][ny] <= nc) continue;

                if (num[nx][ny] > 0) {
                    road[number][num[nx][ny]] = Math.min(nc, road[number][num[nx][ny]]);
                }

                check[nx][ny] = nc;
                isVisited[nx][ny] = true;
                Position next = new Position(nx, ny);
                next.cnt = nc;
                pq.offer(next);
            }
        }
    }

    private static void setIsLandNumber(int x, int y, int cnt) {
        boolean[][] v = new boolean[R][C];
        Queue<Position> q = new ArrayDeque<>();

        q.add(new Position(x, y));
        v[x][y] = true;
        num[x][y] = cnt;

        while (!q.isEmpty()) {
            Position now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;
                if(v[nx][ny] || map[nx][ny] != 'X') continue;

                q.add(new Position(nx, ny));
                v[nx][ny] = true;
                num[nx][ny] = cnt;
            }
        }
    }
}
