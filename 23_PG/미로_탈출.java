import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 미로_탈출 {
    public int solution(String[] maps) {
        int[] start = new int[2];
        int[] end = new int[2];
        int[] lever = new int[2];

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if(maps[i].charAt(j) == 'S') start = new int[]{i, j};
                else if(maps[i].charAt(j) == 'E') end = new int[]{i, j};
                else if(maps[i].charAt(j) == 'L') lever = new int[]{i, j};
            }
        }

        int s = findDist(start, lever, maps);
        if(s == Integer.MAX_VALUE) return -1;

        int e = findDist(lever, end, maps);
        if(e == Integer.MAX_VALUE) return -1;

        return s + e;
    }

    private int findDist(int[] from, int[] to, String[] maps) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        int n = maps.length;
        int m = maps[0].length();

        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        q.add(new int[]{from[0], from[1], 0});
        dist[from[0]][from[1]] = 0;


        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || n <= nx || m <= ny) continue;
                if(now[2] + 1 >= dist[nx][ny] || maps[nx].charAt(ny) == 'X') continue;

                dist[nx][ny] = now[2] + 1;
                q.add(new int[]{nx, ny, now[2] + 1});
            }
        }

        return dist[to[0]][to[1]];
    }
}
