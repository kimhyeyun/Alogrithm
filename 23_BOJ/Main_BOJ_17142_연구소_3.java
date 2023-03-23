import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_17142_연구소_3 {
    static int N, M, answer;
    static int[][] labotory;
    static List<int[]> viruses;
    static int[] isActivatedViruses;
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = Integer.MAX_VALUE;

        labotory = new int[N][N];
        viruses = new LinkedList<>();
        isActivatedViruses = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                labotory[i][j] = Integer.parseInt(st.nextToken());
                if(labotory[i][j] == 2) viruses.add(new int[]{i, j, 0});
            }
        }

        DFS(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void DFS(int count, int index) {
        if (count == M) {
            calSpreadMinTime();
            return;
        }

        for (int i = index; i < viruses.size(); i++) {
            isActivatedViruses[count] = i;
            DFS(count + 1, i + 1);
        }
    }

    private static void calSpreadMinTime() {
        Queue<int[]> q = new LinkedList<>();

        int[][] tmp = new int[N][N];
        int[][] time = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
            System.arraycopy(labotory[i], 0, tmp[i], 0, N);
        }

        for (int index : isActivatedViruses) {
            int[] v = viruses.get(index);
            q.add(v);

            tmp[v[0]][v[1]] = -1;
            time[v[0]][v[1]] = 0;
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(tmp[nx][ny] == 1 || time[nx][ny] <= now[2] + 1) continue;

                time[nx][ny] = now[2] + 1;
                q.add(new int[]{nx, ny, time[nx][ny]});

                if(tmp[nx][ny] == 2) tmp[nx][ny] = -2;
                else tmp[nx][ny] = -1;
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(tmp[i][j] == 0) return;
                if(tmp[i][j] == -2) continue;
                if (tmp[i][j] == -1) {
                    if(time[i][j] == Integer.MAX_VALUE) return;
                    max = Math.max(max, time[i][j]);
                }
            }
        }

        answer = Math.min(answer, max);
    }
}
