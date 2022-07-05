import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_17142_연구소_3 {
    static int N, M, ans = Integer.MAX_VALUE;
    static int[][] labotory, time, active;
    static List<int[]> virus;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        virus = new ArrayList<>();
        active = new int[M][2];

        labotory = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                labotory[i][j] = Integer.parseInt(stringTokenizer.nextToken());

                if(labotory[i][j] == 2) virus.add(new int[]{i, j, 0});
            }
        }


        DFS(0, 0);

        if(ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);

    }

    private static void DFS(int cnt, int idx) {
        if (cnt == M) {
            BFS();
            return;
        }

        for (int i = idx; i < virus.size(); i++) {
            active[cnt] = virus.get(i);
            DFS(cnt + 1, i + 1);
        }
    }

    private static void BFS() {
        Queue<int[]> q = new LinkedList<>();
        time = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }

        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(labotory[i], 0, tmp[i], 0, N);
        }

        for (int i = 0; i < M; i++) {
            int[] v = active[i];
            q.add(v);
            time[v[0]][v[1]] = 0;
            tmp[v[0]][v[1]] = -1;
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny || tmp[nx][ny] == 1) continue;

                if (time[nx][ny] > now[2] + 1) {
                    q.add(new int[]{nx, ny, now[2] + 1});
                    if(tmp[nx][ny] == 2) tmp[nx][ny] = -2;
                    else tmp[nx][ny] = -1;
                    time[nx][ny] = now[2] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(tmp[i][j] == 0) return;
                if(tmp[i][j] == -2) continue;
                if (tmp[i][j] == -1) {
                    if(time[i][j] == Integer.MAX_VALUE) return;
                    max = max < time[i][j] ? time[i][j] : max;
                }
            }
        }

        ans = max < ans ? max : ans;
    }
}
