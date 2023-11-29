import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main_BOJ_1941_소문난_칠공주 {
    static int answer;
    static char[][] map;
    static boolean[] isVisited;
    static int[] checked;

    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        answer = 0;

        map = new char[5][5];
        checked = new int[7];

        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        comb(0, 0, 0);

        System.out.println(answer);
    }

    private static void comb(int count, int start, int dasomCount) {
        if(count - dasomCount > 3) return;
        if (count == 7) {
            isVisited = new boolean[7];
            BFS(checked[0] / 5, checked[0] % 5);
            return;
        }

        for (int i = start; i < 25; i++) {
            int x = i / 5;
            int y = i % 5;

            checked[count] = i;
            comb(count + 1, i + 1, (map[x][y] == 'S') ? dasomCount + 1 : dasomCount);
        }
    }

    private static void BFS(int x, int y) {
        int num = 1;
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{x, y});
        isVisited[0] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || 5 <= nx || 5 <= ny) continue;

                int next = 5 * nx + ny;
                for (int k = 0; k < 7; k++) {
                    if(isVisited[k] || checked[k] != next) continue;

                    isVisited[k] = true;
                    num += 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        if(num == 7) answer += 1;
    }
}
