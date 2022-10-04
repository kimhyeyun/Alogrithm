import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class n_15685_드래곤_커브 {
    static final int MAX = 101;
    static int N;
    static boolean[][] rectangle;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static List<Integer> curves;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        rectangle = new boolean[MAX][MAX];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            curves = new ArrayList<>();

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
                if (rectangle[i][j] && rectangle[i][j + 1] && rectangle[i + 1][j] && rectangle[i + 1][j + 1]) {
                    answer += 1;
                }
            }
        }
        return answer;
    }

    private static void drawCurves(int x, int y) {
        rectangle[x][y] = true;

        for (int curve : curves) {
            x += dx[curve];
            y += dy[curve];

            rectangle[x][y] = true;
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
