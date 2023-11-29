import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_1938_통나무_옮기기 {
    static int N, sp, ep;
    static int[][] board;
    static List<int[]> start, end;
    static boolean[][][] isVisited;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        start = new LinkedList<>();
        end = new LinkedList<>();

        isVisited = new boolean[N][N][2];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                if(c == 'B') start.add(new int[]{i, j});
                else if(c == 'E') end.add(new int[]{i, j});
                else board[i][j] = c - '0';
            }
        }

        posCheck();
        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{start.get(1)[0], start.get(1)[1], sp, 0});
        isVisited[start.get(1)[0]][start.get(1)[1]][sp] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];
            int pos = cur[2];
            int count = cur[3];

            if (x == end.get(1)[0] && y == end.get(1)[1] && pos == ep) {
                return count;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!range_check(nx, ny, pos)) continue;
                if(isVisited[nx][ny][pos]) continue;
                if(isTree(nx,ny,pos)) continue;

                isVisited[nx][ny][pos] = true;
                q.offer(new int[]{nx, ny, pos, count + 1});
            }

            if (isPossibleTurning(x, y, pos)) {
                if(pos == 1) q.offer(new int[]{x, y, 0, count + 1});
                else q.offer(new int[]{x, y, 1, count + 1});
            }
        }
        return 0;
    }

    private static boolean isPossibleTurning(int x, int y, int pos) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(i == 0 && j == 0) continue;

                int nx = x + i;
                int ny = y + j;

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(board[nx][ny] == 1) return false;
            }
        }

        pos = pos == 1 ? 0 : 1;

        if(!range_check(x, y, pos) || isTree(x, y, pos)) return false;
        if(isVisited[x][y][pos]) return false;

        isVisited[x][y][pos] = true;

        return true;
    }

    private static boolean isTree(int x, int y, int pos) {
        for (int i = -1; i <= 1; i++) {
            if (pos == 1) {
                int nx = x + i;
                if (board[nx][y] == 1) return true;
            } else {
                int ny = y + i;
                if(board[x][ny] == 1) return true;
            }
        }
        return false;
    }

    private static boolean range_check(int x, int y, int pos) {
        if (pos == 1) {
            if (x <= 0 || N - 1 <= x || y < 0 || N <= y) return false;
        } else {
            if(x < 0 || N <= x || y <= 0 || N - 1 <= y) return false;
        }
        return true;
    }

    private static void posCheck() {
        int num = start.get(0)[0] - start.get(1)[0];
        if(num == 1 || num == -1) sp = 1;

        num = end.get(0)[0] - end.get(1)[0];
        if(num == 1 || num == -1) ep = 1;
    }
}
