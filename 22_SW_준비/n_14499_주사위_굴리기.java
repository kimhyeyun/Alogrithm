import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n_14499_주사위_굴리기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[] horizontal = new int[4];
        int[] vertical = new int[4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {0, 0, 0, -1, 1};
        int[] dy = {0, 1, -1, 0, 0};

        String str;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int dir = Integer.parseInt(st.nextToken());

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;

            if(dir == 1 || dir == 2){
                moveDice(horizontal, dir);
                vertical[1] = horizontal[1];
                vertical[3] = horizontal[3];
            } else{
                moveDice(vertical, dir);
                horizontal[1] = vertical[1];
                horizontal[3] = vertical[3];
            }

            if(map[nx][ny] == 0){ map[nx][ny] = horizontal[3];}
            else{
                horizontal[3] = vertical[3] = map[nx][ny];
                map[nx][ny] = 0;
            }

            x = nx;
            y = ny;

            System.out.println(horizontal[1]);

        }

    }

    private static void moveDice(int[] dice, int d) {
        if (d == 1 || d == 4) {
            int tmp = dice[3];
            for (int i = 3; i > 0; i--) {
                dice[i] = dice[i - 1];
            }
            dice[0] = tmp;
        } else {
            int tmp = dice[0];
            for (int i = 0; i < 3; i++) {
                dice[i] = dice[i + 1];
            }
            dice[3] = tmp;
        }
    }
}
