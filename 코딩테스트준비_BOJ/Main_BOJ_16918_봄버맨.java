import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_16918_봄버맨 {
    static int R, C, N;
    static char[][] map;
    static List<int[]> bombs;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        bombs = new ArrayList<>();
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'O') bombs.add(new int[]{i, j});
            }
        }

        if (N % 2 == 0) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print("O");
                }
                System.out.println();
            }
            return;
        }

        for (int i = 2; i <= N; i++) {
            if(i % 2 == 0) setBombs();
            else explodeBombs();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void explodeBombs() {
        for (int[] bomb : bombs) {
            map[bomb[0]][bomb[1]] = '.';
            for (int d = 0; d < 4; d++) {
                int nx = bomb[0] + dx[d];
                int ny = bomb[1] + dy[d];

                if(nx < 0 || ny < 0 || R <= nx || C <= ny) continue;

                map[nx][ny] = '.';
            }
        }

        bombs.clear();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'O') bombs.add(new int[]{i, j});
            }
        }
    }

    private static void setBombs() {
        for (int i = 0; i < R; i++) {
            Arrays.fill(map[i], 'O');
        }
    }
}
