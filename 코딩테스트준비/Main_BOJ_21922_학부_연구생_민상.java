import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_21922_학부_연구생_민상 {
    static int N, M;
    static int[][] lab;
    static boolean[][][] isWindy;
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 0, 1}; // 위, 왼, 오, 아래
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        lab = new int[N][M];
        isWindy = new boolean[N][M][4];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(stringTokenizer.nextToken());

                if (lab[i][j] == 9) {
                    for (int d = 0; d < 4; d++) {
                        queue.add(new int[]{i, j, d});
                        isWindy[i][j][d] = true;
                    }
                }
            }
        }

        BFS();

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 4; d++) {
                    if(isWindy[i][j][d]){
                        ans += 1;
                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static void BFS() {
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int dir = now[2];
            int nx = now[0] + dx[dir];
            int ny = now[1] + dy[dir];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;
            if(isWindy[nx][ny][dir]) continue;

            isWindy[nx][ny][dir] = true;

            switch (lab[nx][ny]) {
                case 1:
                    if(dir == 1 || dir == 2) continue;
                    break;
                case 2:
                    if(dir == 0 || dir == 3) continue;
                    break;
                case 3:
                    if(dir == 0) dir = 2;
                    else if(dir == 1) dir = 3;
                    else if(dir == 2) dir = 0;
                    else dir = 1;
                    break;
                case 4:
                    if(dir == 0) dir = 1;
                    else if(dir == 1) dir = 0;
                    else if(dir == 2) dir = 3;
                    else dir = 2;
                    break;
            }

            queue.add(new int[]{nx, ny, dir});
        }
    }
}
