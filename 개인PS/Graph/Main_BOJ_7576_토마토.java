package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_7576_토마토 {
    static int N, M;
    static int[][] tomatoes, days;
    static Queue<int[]> q;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        M = Integer.parseInt(stringTokenizer.nextToken());
        N = Integer.parseInt(stringTokenizer.nextToken());

        tomatoes = new int[N][M];
        days = new int[N][M];
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomatoes[i][j] = Integer.parseInt(stringTokenizer.nextToken());

                if(tomatoes[i][j] == 1){
                    q.add(new int[]{i, j});
                    days[i][j] = 1;
                }

                else if(tomatoes[i][j] == -1) days[i][j] = 1;
            }
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;

                if (tomatoes[nx][ny] == 0 && (days[nx][ny] == 0 || days[nx][ny] > days[now[0]][now[1]] + 1)) {
                    days[nx][ny] = days[now[0]][now[1]] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        int ans = 0;
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = ans < days[i][j] ? days[i][j] : ans;
                if (days[i][j] == 0) {
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }

        System.out.println(flag ? -1 : ans - 1);
    }
}
