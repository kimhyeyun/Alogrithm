import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14499_주사위_굴리기 {
    static int N, M, K, x, y;
    static int[][] map;
    static int[] horizonOfDice, verticalOfDice;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        horizonOfDice = new int[4];
        verticalOfDice = new int[4];

        st = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            int dir = Integer.parseInt(st.nextToken()) - 1;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) continue;

            if(dir == 0){
                int tmp = horizonOfDice[3];
                for (int i = 3; i > 0; i--) {
                    horizonOfDice[i] = horizonOfDice[i - 1];
                }
                horizonOfDice[0] = tmp;

                if(map[nx][ny] != 0) {
                    horizonOfDice[3] = map[nx][ny];
                    map[nx][ny] = 0;
                }
                else map[nx][ny] = horizonOfDice[3];

                verticalOfDice[1] = horizonOfDice[1];
                verticalOfDice[3] = horizonOfDice[3];
            }else if(dir == 1){
                int tmp = horizonOfDice[0];
                for (int i = 0; i < 3; i++) {
                    horizonOfDice[i] = horizonOfDice[i + 1];
                }
                horizonOfDice[3] = tmp;

                if(map[nx][ny] != 0) {
                    horizonOfDice[3] = map[nx][ny];
                    map[nx][ny] = 0;
                }
                else map[nx][ny] = horizonOfDice[3];

                verticalOfDice[1] = horizonOfDice[1];
                verticalOfDice[3] = horizonOfDice[3];
            } else if (dir == 2) {
                int tmp = verticalOfDice[3];
                for (int i = 3; i > 0; i--) {
                    verticalOfDice[i] = verticalOfDice[i - 1];
                }
                verticalOfDice[0] = tmp;

                if(map[nx][ny] != 0) {
                    verticalOfDice[3] = map[nx][ny];
                    map[nx][ny] = 0;
                }
                else map[nx][ny] = verticalOfDice[3];

                horizonOfDice[1] = verticalOfDice[1];
                horizonOfDice[3] = verticalOfDice[3];
            }else{
                int tmp = verticalOfDice[0];
                for (int i = 0; i < 3; i++) {
                    verticalOfDice[i] = verticalOfDice[i + 1];
                }
                verticalOfDice[3] = tmp;

                if(map[nx][ny] != 0) {
                    verticalOfDice[3] = map[nx][ny];
                    map[nx][ny] = 0;
                }
                else map[nx][ny] = verticalOfDice[3];

                horizonOfDice[1] = verticalOfDice[1];
                horizonOfDice[3] = verticalOfDice[3];
            }

            x = nx;
            y = ny;

            System.out.println(horizonOfDice[1]);
        }
    }
}
