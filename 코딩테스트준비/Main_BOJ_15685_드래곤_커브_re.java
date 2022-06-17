import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_15685_드래곤_커브_re {
    static final int MAX = 101;
    static int N, x, y ,d , g;
    static boolean[][] map;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static List<Integer> curveList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        N = Integer.parseInt(br.readLine());
        map = new boolean[MAX][MAX];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            y = Integer.parseInt(stringTokenizer.nextToken());
            x = Integer.parseInt(stringTokenizer.nextToken());
            d = Integer.parseInt(stringTokenizer.nextToken());
            g = Integer.parseInt(stringTokenizer.nextToken());

            curveList = new ArrayList<>();
            curveList.add(d);
            makeCurve(g);
            drawCurve(x, y);
        }
        System.out.println(checkRectangle());

    }

    private static int checkRectangle() {
        int ans = 0;
        for (int i = 0; i < MAX - 1; i++) {
            for (int j = 0; j < MAX - 1; j++) {
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) ans += 1;
            }
        }
        return ans;
    }

    private static void drawCurve(int x, int y) {
        map[x][y] = true;
        for (int dir : curveList) {
            x += dx[dir];
            y += dy[dir];

            map[x][y] = true;
        }
    }

    private static void makeCurve(int generation) {
        for (int g = 0; g < generation; g++) {
            for (int s = curveList.size() - 1; s >= 0; s--) {
                curveList.add((curveList.get(s) + 1) % 4);
            }
        }
    }
}
