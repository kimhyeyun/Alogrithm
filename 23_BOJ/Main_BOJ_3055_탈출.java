import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_3055_탈출 {
    static int R, C, answer;
    static char[][] map;
    static Queue<int[]> q, water;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        answer = Integer.MAX_VALUE;

        map = new char[R][C];
        q = new LinkedList<>();
        water = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'S') q.add(new int[]{i, j, 0});
                else if(map[i][j] == '*') water.add(new int[]{i, j});
            }
        }

        BFS();

        System.out.println(answer == Integer.MAX_VALUE ? "KAKTUS" : answer);

    }

    private static void BFS() {
        while (!q.isEmpty()) {
            int size = water.size();

            for (int i = 0; i < size; i++) {
                int[] cur = water.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur[0] + dx[d];
                    int ny = cur[1] + dy[d];

                    if(nx < 0 || ny < 0 || R <= nx || C <= ny || map[nx][ny] != '.') continue;

                    map[nx][ny] = '*';
                    water.add(new int[]{nx, ny});
                }
            }

            size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur[0] + dx[d];
                    int ny = cur[1] + dy[d];

                    if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;
                    if (map[nx][ny] == 'D') {
                        answer = Math.min(answer, cur[2] + 1);
                        return;
                    } else if (map[nx][ny] == '.') {
                        map[nx][ny] = 'S';
                        q.add(new int[]{nx, ny, cur[2] + 1});
                    }
                }
            }
        }
    }
}
