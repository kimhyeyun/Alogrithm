import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class L2_게임_맵_최단거리 {
    public static int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        return BFS(maps, n, m);
    }

    private static int BFS(int[][] maps, int n, int m) {
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        Queue<int[]> q = new LinkedList<>();

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        q.add(new int[]{0, 0, 1});
        dist[0][0] = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || n <= nx || m <= ny) continue;
                if(dist[nx][ny] <= now[2] + 1 || maps[nx][ny] == 0) continue;

                if (nx == n - 1 && ny == m - 1) return now[2] + 1;


                dist[nx][ny] = now[2] + 1;
                q.add(new int[]{nx, ny, now[2] + 1});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        System.out.println(solution(maps));
    }
}
