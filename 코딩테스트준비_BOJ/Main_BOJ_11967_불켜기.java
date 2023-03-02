import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_11967_불켜기 {
    static int N, M, answer;
    static List<int[]>[][] switches;
    static boolean[][] isLighted, isMoved, isVisited;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isLighted = new boolean[N][N];
        isVisited = new boolean[N][N];
        isMoved = new boolean[N][N];
        switches = new List[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                switches[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            switches[x1][y1].add(new int[]{x2, y2});
        }

        answer = 1;
        isLighted[0][0] = isVisited[0][0] = true;

        BFS();

        System.out.println(answer);
    }

    private static void BFS() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;

                isMoved[nx][ny] = true;
            }

            for (int[] room : switches[now[0]][now[1]]) {
                if (!isLighted[room[0]][room[1]]) {
                    isLighted[room[0]][room[1]] = true;
                    answer += 1;
                }

                if (isMoved[room[0]][room[1]] && !isVisited[room[0]][room[1]]) {
                    isVisited[room[0]][room[1]] = true;
                    q.add(new int[]{room[0], room[1]});
                }
            }

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(isVisited[nx][ny] || !isMoved[nx][ny] || !isLighted[nx][ny]) continue;

                isVisited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }

        }
    }
}
