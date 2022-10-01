import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n_15683_감시 {
    static int N, M, answer;
    static int[][] officeRoom;
    static List<int[]> cctvs;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = Integer.MAX_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        officeRoom = new int[N][M];
        cctvs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                officeRoom[i][j] = Integer.parseInt(st.nextToken());
                if(1 <= officeRoom[i][j] && officeRoom[i][j] <= 5) cctvs.add(new int[]{i, j, officeRoom[i][j]});
            }
        }

        solution(0, 0, officeRoom);

        System.out.println(answer);
    }

    private static void solution(int idx, int cnt, int[][] prevRoom) {
        int[][] tmpRoom = new int[N][M];
        if (cnt == cctvs.size()) {
            int c = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(prevRoom[i][j] == 0) c += 1;
                }
            }
            answer = Math.min(answer, c);
            return;
        }

        for (int i = idx; i < cctvs.size(); i++) {
            int[] now = cctvs.get(i);

            if (now[2] == 1) {
                for (int d = 0; d < 4; d++) {
                    for (int k = 0; k < N; k++) tmpRoom[k] = prevRoom[k].clone();
                    detect(now[0], now[1], d, tmpRoom);
                    solution(i + 1, cnt + 1, tmpRoom);
                }
            } else if (now[2] == 2) {
                for (int d = 0; d < 2; d++) {
                    for (int k = 0; k < N; k++) tmpRoom[k] = prevRoom[k].clone();
                    detect(now[0], now[1], d, tmpRoom);
                    detect(now[0], now[1], d + 2, tmpRoom);
                    solution(i + 1, cnt + 1, tmpRoom);
                }
            } else if (now[2] == 3) {
                for (int d = 0; d < 4; d++) {
                    for (int k = 0; k < N; k++) tmpRoom[k] = prevRoom[k].clone();
                    detect(now[0], now[1], d, tmpRoom);
                    int nextd = d + 1 == 4 ? 0 : d + 1;
                    detect(now[0], now[1], nextd, tmpRoom);
                    solution(i + 1, cnt + 1, tmpRoom);
                }
            } else if (now[2] == 4) {
                for (int d = 0; d < 4; d++) {
                    for (int k = 0; k < N; k++) tmpRoom[k] = prevRoom[k].clone();
                    int pd = d - 1 == -1 ? 3 : d - 1;
                    int nd = d + 1 == 4 ? 0 : d + 1;
                    detect(now[0], now[1], pd, tmpRoom);
                    detect(now[0], now[1], d, tmpRoom);
                    detect(now[0], now[1], nd, tmpRoom);
                    solution(i + 1, cnt + 1, tmpRoom);
                }
            } else {
                for (int k = 0; k < N; k++) tmpRoom[k] = prevRoom[k].clone();
                for (int d = 0; d < 4; d++) {
                    detect(now[0], now[1], d, tmpRoom);
                }
                solution(i + 1, cnt + 1, tmpRoom);
            }
        }
    }

    private static void detect(int x, int y, int dir, int[][] room){
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{x, y});
        room[x][y] = -1;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int nx = now[0] + dx[dir];
            int ny = now[1] + dy[dir];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
            if(room[nx][ny] == 6) continue;

            room[nx][ny] = -1;
            q.add(new int[]{nx, ny});
        }

    }
}
