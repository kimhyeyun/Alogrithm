import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class code_tree_술래잡기 {
    static int n, m, h, k;
    static List<Integer>[][] hiders, nextHiders;
    static boolean[][] tree;
    static int[][] seekerNextDir, seekerCurDir;
    static int[] seekerPos;
    static boolean fowardFacing = true;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        hiders = new List[n][n];
        tree = new boolean[n][n];
        seekerNextDir = new int[n][n];
        seekerCurDir = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                hiders[i][j] = new ArrayList<>();
            }
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            hiders[x - 1][y - 1].add(d);
        }

        while (h-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            tree[x - 1][y - 1] = true;
        }

        seekerPos = new int[]{n / 2, n / 2};

        initSeekerPath();

        for (int t = 1; t <= k; t++) {
            simulate(t);
        }
        System.out.println(answer);
    }

    private static void simulate(int t) {
        hiderMoveAll();
        seekerMove();
        getScore(t);
    }

    private static void getScore(int t) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int x = seekerPos[0];
        int y = seekerPos[1];

        int moveDir = getSeekerDir();

        for (int dist = 0; dist < 3; dist++) {
            int nx = x + dist * dx[moveDir];
            int ny = y + dist * dy[moveDir];

            if (inRange(nx, ny) && !tree[nx][ny]) {
                answer += t * hiders[nx][ny].size();
                hiders[nx][ny] = new ArrayList<>();
            }
        }
    }

    private static void seekerMove() {
        int x = seekerPos[0];
        int y = seekerPos[1];

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int moveDir = getSeekerDir();
        seekerPos = new int[]{x + dx[moveDir], y + dy[moveDir]};

        checkFacing();
    }

    private static void checkFacing() {
        if ((seekerPos[0] == 0 && seekerPos[1] == 0) && fowardFacing) fowardFacing = false;
        if ((seekerPos[0] == n / 2 && seekerPos[1] == n / 2) && !fowardFacing) fowardFacing = true;

    }

    private static int getSeekerDir() {
        int x = seekerPos[0];
        int y = seekerPos[1];

        int moveDir;
        if (fowardFacing) moveDir = seekerNextDir[x][y];
        else moveDir = seekerCurDir[x][y];

        return moveDir;
    }

    private static void hiderMoveAll() {
        nextHiders = new List[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nextHiders[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (distFromSeeker(i, j) <= 3) {
                    for (int k = 0; k < hiders[i][j].size(); k++) {
                        hiderMove(i, j, hiders[i][j].get(k));
                    }
                } else {
                    for (int k = 0; k < hiders[i][j].size(); k++) {
                        nextHiders[i][j].add(hiders[i][j].get(k));
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                hiders[i][j] = new ArrayList<>(nextHiders[i][j]);
            }
        }
    }

    private static void hiderMove(int x, int y, int moveDir) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {-1, 1, 0, 0};

        int nx = x + dx[moveDir];
        int ny = y + dy[moveDir];

        if (!inRange(nx, ny)) {
            moveDir = (moveDir == 0 || moveDir == 2) ? moveDir + 1 : moveDir - 1;
            nx = x + dx[moveDir];
            ny = y + dy[moveDir];
        }

        if (nx != seekerPos[0] || ny != seekerPos[1]) nextHiders[nx][ny].add(moveDir);
        else nextHiders[x][y].add(moveDir);
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static int distFromSeeker(int x, int y) {
        return Math.abs(seekerPos[0] - x) + Math.abs(seekerPos[1] - y);
    }

    private static void initSeekerPath() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int curX = n / 2, curY = n / 2;
        int moveDir = 0, moveNum = 1;

        while (curX > 0 || curY > 0) {
            for (int i = 0; i < moveNum; i++) {
                seekerNextDir[curX][curY] = moveDir;
                curX += dx[moveDir];
                curY += dy[moveDir];
                seekerCurDir[curX][curY] = (moveDir < 2) ? (moveDir + 2) : (moveDir - 2);

                if (curX == 0 && curY == 0) break;
            }

            moveDir = (moveDir + 1) % 4;
            if (moveDir == 0 || moveDir == 2) moveNum += 1;
        }
    }
}
