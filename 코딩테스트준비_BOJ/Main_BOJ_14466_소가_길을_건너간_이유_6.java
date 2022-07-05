import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main_BOJ_14466_소가_길을_건너간_이유_6 {
    static int N, K, R, ans = 0;
    static boolean[][] isVisited;
    static Point[] cows;
    static List<Point>[][] bridge;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());
        R = Integer.parseInt(stringTokenizer.nextToken());

        bridge = new List[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bridge[i][j] = new ArrayList<>();
            }
        }

        while (R-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y1 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int x2 = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y2 = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            bridge[x1][y1].add(new Point(x2, y2));
            bridge[x2][y2].add(new Point(x1, y1));
        }

        cows = new Point[K];
        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int y = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            cows[i] = new Point(x, y);
        }

        solution();

        System.out.println(ans);
    }

    private static void solution() {
        for (int i = 0; i < K; i++) {
            isVisited = new boolean[N][N];
            move(cows[i].x, cows[i].y);

            for (int j = i; j < K; j++) {
                Point c = cows[j];
                if(!isVisited[c.x][c.y]) ans += 1;
            }
        }
    }

    private static void move(int x, int y) {
        isVisited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
            if(isVisited[nx][ny]) continue;
            if(bridge[x][y].contains(new Point(nx,ny))) continue;

            move(nx, ny);
        }
    }
}
