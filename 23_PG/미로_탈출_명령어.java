import java.util.Map;

public class 미로_탈출_명령어 {

    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    char[] pos = {'d', 'l', 'r', 'u'};
    boolean stop = false;
    String answer;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "impossible";

        int[] start = new int[]{x - 1, y - 1};
        int[] end = new int[]{r - 1, c - 1};

        if(x == r && y == c) return "";
        if (!canArrival(start, end, k)) return answer;

        DFS(start, end, k - 1, n, m, "");

        return answer;
    }

    private void DFS(int[] now, int[] end, int count, int n, int m, String cur) {
        if(stop || count < 0) return;

        for (int d = 0; d < 4; d++) {
            if(stop) return;
            int nx = now[0] + dx[d];
            int ny = now[1] + dy[d];

            if(nx < 0 || ny < 0 || n <= nx || m <= ny || !canArrival(new int[]{nx,ny}, end, count)) continue;
            if(count > 0) DFS(new int[]{nx, ny}, end, count - 1, n, m, cur + pos[d]);
            if (nx == end[0] && ny == end[1] && count == 0) {
                stop = true;
                answer = cur + pos[d];
                return;
            }
        }
    }

    private boolean canArrival(int[] start, int[] end, int k) {
        int d = getDist(start, end);

        if(d > k || (k - d) % 2 == 1) return false;
        return true;
    }

    private int getDist(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }

}
