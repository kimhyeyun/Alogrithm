import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_15685_드래곤_커브 {
    static int N;
    static boolean[][] map;
    static List<Integer> curves;
    static final int MAX = 101;
    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new boolean[MAX][MAX];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            curves = new LinkedList<>();

            curves.add(d);
            makeCurves(g);
            drawCurves(x, y);
        }

        System.out.println(check());
    }

    private static int check() {
        int answer = 0;

        for (int i = 0; i < MAX - 1; i++) {
            for (int j = 0; j < MAX - 1; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) answer += 1;
            }
        }
        return answer;
    }

    private static void drawCurves(int x, int y) {
        map[x][y] = true;

        for (int curve : curves) {
            x += dx[curve];
            y += dy[curve];

            map[x][y] = true;
        }
    }

    private static void makeCurves(int generation) {
        for (int g = 0; g < generation; g++) {
            for (int i = curves.size() - 1; i >= 0; i--) {
                curves.add((curves.get(i) + 1) % 4);
            }
        }
    }
}
