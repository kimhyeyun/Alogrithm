import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_5212_지구_온난화 {

    static int R, C, minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = -1, maxY = -1;
    static int[][] map, aroundSea;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        aroundSea = new int[R][C];
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = arr[j] == '.' ? 0 : 1;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 1) findSeaCnt(i, j);
            }
        }
        removeLand();
        makeMap();
        /*System.out.println("minX : " + minX + " minY : " + minY +" maxX : " + maxX + " maxY : " + maxY);

        for (int[] ma : map) {
            for (int m : ma) {
                System.out.print(m + " ");
            }
            System.out.println();
        }*/

        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                System.out.print(map[i][j] == 0 ? '.' : 'X');
            }
            System.out.println();
        }
    }

    private static void makeMap() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) {
                    minX = minX > i ? i : minX;
                    minY = minY > j ? j : minY;
                    maxX = maxX < i ? i : maxX;
                    maxY = maxY < j ? j : maxY;
                }
            }
        }
    }

    private static void removeLand() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (aroundSea[i][j] >= 3) {
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void findSeaCnt(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || R <= nx || C <= ny || map[nx][ny] == 0) aroundSea[x][y] += 1;
        }
    }
}
