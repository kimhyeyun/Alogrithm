import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n_17142_연구소_3 {
    static int N, M, answer;
    static int[][] laboratory;
    static int[] isActivatedVirus;
    static List<int[]> virusList;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        laboratory = new int[N][N];
        isActivatedVirus = new int[M];
        virusList = new ArrayList<>();
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                laboratory[i][j] = Integer.parseInt(st.nextToken());
                if(laboratory[i][j] == 2) virusList.add(new int[]{i, j, 0});
            }
        }
        solution(0, 0);
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void solution(int cnt, int idx) {
        if (cnt == M) {
            spreadVirus();
            return;
        }

        for (int i = idx; i < virusList.size(); i++) {
            isActivatedVirus[cnt] = i;
            solution(cnt + 1, i + 1);
        }
    }

    private static void spreadVirus() {
        Queue<int[]> q = new LinkedList<>();
        int[][] tmp = new int[N][N];
        int[][] time = new int[N][N];

        for (int i = 0; i < N; i++) {
            System.arraycopy(laboratory[i], 0, tmp[i], 0, N);
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }

        for (int idx : isActivatedVirus) {
            int[] virus = virusList.get(idx);
            q.add(virus);
            time[virus[0]][virus[1]] = 0;
            tmp[virus[0]][virus[1]] = -1;
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
                if(tmp[nx][ny] == 1 || time[nx][ny] <= now[2] + 1) continue;

                q.add(new int[]{nx, ny, now[2] + 1});
                time[nx][ny] = now[2] + 1;

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
                    if (time[i][j] == Integer.MAX_VALUE) return;
                    max = Math.max(max, time[i][j]);
                }
            }
        }
        answer = Math.min(answer, max);
    }
}
