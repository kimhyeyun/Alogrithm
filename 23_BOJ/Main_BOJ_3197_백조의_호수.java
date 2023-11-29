import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_3197_백조의_호수 {
    static int R, C;
    static char[][] map;
    static Queue<int[]> waters, q;
    static boolean[][] isVisited;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        isVisited = new boolean[R][C];

        waters = new LinkedList<>();
        q = new LinkedList<>();

        int[] swan = new int[2];
        boolean flag = false;

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L' && !flag) {
                    swan = new int[]{i, j};
                    flag = true;
                }
                if (map[i][j] != 'X') {
                    waters.add(new int[]{i, j});
                }
            }
        }

        q.add(swan);
        isVisited[swan[0]][swan[1]] = true;

        int time = 0;

        while (true) {
            if (isMet()) {
                System.out.println(time);
                return;
            }

            int size = waters.size();
            for (int i = 0; i < size; i++) {
                int[] water = waters.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = water[0] + dx[d];
                    int ny = water[1] + dy[d];

                    if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;

                    if (map[nx][ny] == 'X') {
                        map[nx][ny] = '.';
                        waters.add(new int[]{nx, ny});
                    }
                }
            }

            time += 1;
        }
    }

    private static boolean isMet() {
        Queue<int[]> next = new LinkedList<>();

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || R <= nx || C <= ny || isVisited[nx][ny]) continue;

                isVisited[nx][ny] = true;

                if (map[nx][ny] == 'X') {
                    next.add(new int[]{nx, ny});
                    continue;
                }

                if(map[nx][ny] == 'L') return true;

                q.add(new int[]{nx, ny});
            }
        }

        q = next;
        return false;
    }
}
