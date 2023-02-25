import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_2638_치즈 {
    static int N, M;
    static int[][] map;
    static int cheeseCount;
    static List<int[]> cheeses;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static final int CHEESE = 1, OUTER_AIR = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cheeseCount = 0;
        cheeses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == CHEESE){
                    cheeseCount += 1;
                    cheeses.add(new int[]{i, j});
                }
            }
        }

        int answer = 0;
        while (cheeseCount > 0) {
            answer += 1;
            splitAir();
            meltCheese();
        }

        System.out.println(answer);
    }

    private static void meltCheese() {
        for (int i = 0; i < cheeses.size(); i++) {
            int[] cheese = cheeses.get(i);
            int count = 0;
            for (int d = 0; d < 4; d++) {
                int nx = cheese[0] + dx[d];
                int ny = cheese[1] + dy[d];

                if (nx < 0 || ny < 0 || N <= nx || M <= ny) continue;

                if (map[nx][ny] == OUTER_AIR) count += 1;
            }
            if (count >= 2) {
                map[cheese[0]][cheese[1]] = 0;
                cheeses.remove(i);
                i -= 1;
                cheeseCount -= 1;
            }
        }
    }

    private static void splitAir() {
        boolean[][] isVisited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();

        isVisited[0][0] = true;
        q.add(new int[]{0, 0});
        map[0][0] = OUTER_AIR;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
                if(isVisited[nx][ny] || map[nx][ny] == CHEESE) continue;

                isVisited[nx][ny] = true;
                map[nx][ny] = OUTER_AIR;
                q.add(new int[]{nx, ny});
            }
        }
    }
}
