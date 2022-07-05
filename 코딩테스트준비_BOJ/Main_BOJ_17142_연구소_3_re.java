import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BOJ_17142_연구소_3_re {
    static final int EMPTY = 0, WALL = 1, VIRUS = -1;
    static int N, M, ans = Integer.MAX_VALUE;
    static int[][] lab;
    static List<int[]> virusList;
    static int[][] active;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        virusList = new ArrayList<>();
        lab = new int[N][N];
        active = new int[M][2];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lab[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if(lab[i][j] == 2) virusList.add(new int[]{i, j, 0});
            }
        }

        activateVirus(0, 0);

        if(ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    private static void activateVirus(int idx, int cnt) {
        if (cnt == M) {
            spreadVirus();
            return;
        }

        for (int i = idx; i < virusList.size(); i++) {
            active[cnt] = virusList.get(i);
            activateVirus(i + 1, cnt + 1);
        }
    }

    private static void spreadVirus() {
        Queue<int[]> q = new LinkedList<>();
        int[][] tmp = new int[N][N];
        int[][] time = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
            System.arraycopy(lab[i], 0, tmp[i], 0, N);
        }

        for (int[] a : active) {
            q.add(a);
            tmp[a[0]][a[1]] = VIRUS;
            time[a[0]][a[1]] = 0;
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(tmp[nx][ny] == WALL || time[nx][ny] <= now[2] + 1) continue;

                q.add(new int[]{nx, ny, now[2] + 1});
                if(tmp[nx][ny] == 2) tmp[nx][ny] = -2;
                else tmp[nx][ny] = VIRUS;

                time[nx][ny] = now[2] + 1;
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(tmp[i][j] == 0) return;
                if(tmp[i][j] == -2) continue;
                if (tmp[i][j] == VIRUS) {
                    if(time[i][j] == Integer.MAX_VALUE) return;
                    max = max < time[i][j] ? time[i][j] : max;
                }
            }
        }

        ans = max < ans ? max : ans;

    }
}
