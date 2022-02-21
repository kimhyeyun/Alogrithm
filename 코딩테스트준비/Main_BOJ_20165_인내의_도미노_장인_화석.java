import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20165_인내의_도미노_장인_화석 {
    static int N, M, R, ans = 0;
    static int[][] map;
    static boolean[][] isFallen;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        R = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][M];
        isFallen = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        while (R-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            int dir = 0;
            switch (stringTokenizer.nextToken()) {
                case "E":
                    dir = 2;
                    break;
                case "W":
                    dir = 1;
                    break;
                case "S":
                    dir = 3;
                    break;
                case "N":
                    dir = 0;
                    break;
            }

            if(!isFallen[x][y]) attack(x, y, dir);

            stringTokenizer = new StringTokenizer(br.readLine());
            x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            y = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            depense(x, y);
        }

        System.out.println(ans);
        for (boolean[] is : isFallen) {
            for (boolean i : is) {
                if(i) System.out.print("F ");
                else System.out.print("S ");
            }
            System.out.println();
        }
    }

    private static void depense(int x, int y) {
        if(isFallen[x][y]) isFallen[x][y] = false;
    }

    private static void attack(int x, int y, int dir) {
        int height = map[x][y];
        int nx = x;
        int ny = y;

        while (height > 0) {
            if (!isFallen[nx][ny]) {
                height = height < map[nx][ny] ? map[nx][ny] : height;
                height -= 1;
                isFallen[nx][ny] = true;
                ans += 1;
            } else height -= 1;

            nx += dx[dir];
            ny += dy[dir];

            if(nx < 0 || ny < 0 || N <= nx || M <= ny) break;
        }
    }
}
