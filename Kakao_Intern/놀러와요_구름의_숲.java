import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 놀러와요_구름의_숲 {
    static char[][] forest = new char[10][10];
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] isVisited = new boolean[10][10];
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            forest[i] = br.readLine().toCharArray();
            for (int j = 0; j < 10; j++) {
                if(forest[i][j] == 'H') q.add(new int[]{i, j, 0});
            }
        }

        BFS();

        System.out.println(ans);
    }

    private static void BFS() {
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int tree = now[2];

            isVisited[x][y] = true;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx < 0 || ny < 0 || 10 <= nx || 10 <= ny || forest[nx][ny] == 'R' || isVisited[nx][ny]) continue;

                if(forest[nx][ny] == 'M'){
                    ans = tree < ans ? tree : ans;
                    continue;
                }

                q.add(new int[]{nx, ny, tree + 1});
            }
        }
    }

}
