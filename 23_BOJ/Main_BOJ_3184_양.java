import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_3184_양 {
    static int R, C;
    static int lambs, wolves;
    static char[][] map;
    static boolean[][] isVisited;

    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        lambs = 0;
        wolves = 0;

        map = new char[R][C];
        isVisited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!isVisited[i][j] && map[i][j] != '#') {
                    BFS(i, j);
                }
            }
        }

        System.out.println(lambs + " " + wolves);

    }

    private static void BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        int lamb = 0, wolf = 0;

        if(map[x][y] == 'v') wolf += 1;
        else if(map[x][y] == 'o') lamb += 1;

        q.add(new int[]{x, y});
        isVisited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;
                if(isVisited[nx][ny] || map[nx][ny] == '#') continue;

                q.add(new int[]{nx, ny});
                isVisited[nx][ny] = true;

                if(map[nx][ny] == 'v') wolf += 1;
                else if(map[nx][ny] == 'o') lamb += 1;
            }
        }

        if(wolf >= lamb) wolves += wolf;
        else lambs += lamb;
    }
}
