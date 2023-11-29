import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_tree_메이즈_러너 {
    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int N, M, K;
    public static int answer;
    public static int sx, sy, squareSize;
    public static int[][] maze;
    public static Pos[] travelers;
    public static Pos exit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maze = new int[N][N];
        travelers = new Pos[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            travelers[i] = new Pos(x, y);
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        exit = new Pos(x, y);

        while (K-- > 0) {
            moveAllTraveler();

            boolean isAllEscaped = true;
            for (int i = 0; i < M; i++) {
                if (travelers[i].x == exit.x && travelers[i].y == exit.y) continue;

                isAllEscaped = false;
                break;
            }
            if (isAllEscaped) break;

            findMinSquare();
            rotateSquare();
            rotateTravelersAndExit();
        }

        System.out.println(answer);
        System.out.println((exit.x + 1) + " " + (exit.y + 1));

    }

    private static void rotateTravelersAndExit() {
        for (Pos traveler : travelers) {
            if (sx <= traveler.x && traveler.x < sx + squareSize && sy <= traveler.y && traveler.y < sy + squareSize) {
                int ox = traveler.x - sx;
                int oy = traveler.y - sy;

                int nx = oy, ny = squareSize - ox - 1;
                traveler.x = nx + sx;
                traveler.y = ny + sy;
            }
        }

        if (sx <= exit.x && exit.x < sx + squareSize && sy <= exit.y && exit.y < sy + squareSize) {
            int ox = exit.x - sx;
            int oy = exit.y - sy;

            int nx = oy, ny = squareSize - ox - 1;
            exit.x = nx + sx;
            exit.y = ny + sy;
        }
    }

    private static void rotateSquare() {
        for (int x = sx; x < sx + squareSize; x++) {
            for (int y = sy; y < sy + squareSize; y++) {
                if (maze[x][y] > 0) maze[x][y] -= 1;
            }
        }

        int[][] tmp = new int[N][N];
        int indexX = sx;
        for (int y = sy + squareSize - 1; y >= sy; y--) {
            int indexY = sy;
            for (int x = sx; x < sx + squareSize; x++) {
                tmp[x][y] = maze[indexX][indexY++];
            }
            indexX += 1;
        }

        for (int x = sx; x < sx + squareSize; x++) {
            for (int y = sy; y < sy + squareSize; y++) {
                maze[x][y] = tmp[x][y];
            }
        }
    }

    private static void findMinSquare() {
        for (int size = 2; size <= N; size++) {
            for (int x1 = 0; x1 <= N - size; x1++) {
                for (int y1 = 0; y1 <= N - size; y1++) {
                    int x2 = x1 + size - 1;
                    int y2 = y1 + size - 1;

                    if (!(x1 <= exit.x && exit.x <= x2 && y1 <= exit.y && exit.y <= y2)) continue;

                    boolean isTravelerIn = false;
                    for (int l = 0; l < M; l++) {
                        if (x1 <= travelers[l].x && travelers[l].x <= x2 && y1 <= travelers[l].y && travelers[l].y <= y2) {
                            if (!(travelers[l].x == exit.x && travelers[l].y == exit.y)) isTravelerIn = true;
                        }
                    }

                    if (isTravelerIn) {
                        sx = x1;
                        sy = y1;
                        squareSize = size;

                        return;
                    }

                }
            }
        }
    }

    private static void moveAllTraveler() {
        for (int i = 0; i < M; i++) {
            if (travelers[i].x == exit.x && travelers[i].y == exit.y) continue;

            if (travelers[i].x != exit.x) {
                int nx = travelers[i].x;
                int ny = travelers[i].y;

                if (exit.x > nx) nx += 1;
                else nx -= 1;

                if (maze[nx][ny] == 0) {
                    travelers[i].x = nx;
                    travelers[i].y = ny;
                    answer += 1;
                    continue;
                }
            }

            if (travelers[i].y != exit.y) {
                int nx = travelers[i].x;
                int ny = travelers[i].y;

                if (exit.y > ny) ny += 1;
                else ny -= 1;

                if (maze[nx][ny] == 0) {
                    travelers[i].x = nx;
                    travelers[i].y = ny;
                    answer += 1;
                    continue;
                }
            }
        }
    }
}
