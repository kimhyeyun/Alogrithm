import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_3197_백조의_호수 {
    static int R, C;
    static List<int[]> swans;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static char[][] map;
    static boolean[][] isVisited;
    static Queue<int[]> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[R][C];
        swans = new ArrayList<>();

        Queue<int[]> water = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    swans.add(new int[]{i, j});
                }
                if (map[i][j] != 'X') {
                    water.add(new int[]{i, j});
                }
            }
        }

        int time = 0;
        isVisited = new boolean[R][C];
        q = new LinkedList<>();
        q.add(new int[]{swans.get(0)[0], swans.get(0)[1]});
        isVisited[swans.get(0)[0]][swans.get(0)[1]] = true;

        while (true) {
            if (BFS()) {
                System.out.println(time);
                return;
            }

            int size = water.size();
            for (int i = 0; i < size; i++) {
                int[] w = water.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = w[0] + dx[d];
                    int ny = w[1] + dy[d];

                    if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;

                    if (map[nx][ny] == 'X') {
                        map[nx][ny] = '.';
                        water.add(new int[]{nx, ny});
                    }
                }
            }
            time += 1;
        }
    }

    private static boolean BFS() {
        Queue<int[]> nextQ = new LinkedList<>();

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (nx < 0 || ny < 0 || R <= nx || C <= ny) continue;
                if(isVisited[nx][ny]) continue;

                isVisited[nx][ny] = true;

                if (map[nx][ny] == 'X') {
                    nextQ.add(new int[]{nx, ny});
                    continue;
                }

                if (map[nx][ny] == 'L') return true;

                q.offer(new int[]{nx, ny});
            }
        }

        q = nextQ;
        return false;
    }
}
