import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_tree_메이즈_러너_re {
    public static class Pos {
        int x, y;
        boolean isEscaped;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
            this.isEscaped = false;
        }
    }
    public static int n, m, k, answer;
    public static int sx, sy, minSize;
    public static int[][] maze;
    public static Pos[] participants;
    public static Pos exit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        maze = new int[n][n];
        participants = new Pos[m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            participants[i] = new Pos(x - 1, y - 1);
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        exit = new Pos(x - 1, y - 1);

        while (k-- > 0) {
            moveAllParticipants();

            boolean isAllEscaped = true;
            for (int i = 0; i < m; i++) {
                if (!participants[i].isEscaped){
                    isAllEscaped = false;
                    break;
                }
            }

            if (isAllEscaped) break;

            findMinSquare();
            rotateSquare();
            rotateParticipantsAndExit();
        }

        System.out.println(answer);
        System.out.println((exit.x + 1) + " " + (exit.y + 1));
    }

    private static void rotateParticipantsAndExit() {
        for (Pos participant : participants) {
            if (sx <= participant.x && participant.x < sx + minSize && sy <= participant.y && participant.y < sy + minSize) {
                int ox = participant.x - sx, oy = participant.y - sy;
                int nx = oy, ny = minSize - ox - 1;

                participant.x = nx + sx;
                participant.y = ny + sy;
            }
        }

        if (sx <= exit.x && exit.x < sx + minSize && sy <= exit.y && exit.y < sy + minSize) {
            int ox = exit.x - sx, oy = exit.y - sy;
            int nx = oy, ny = minSize - ox - 1;

            exit.x = nx + sx;
            exit.y = ny + sy;
        }
    }

    private static void rotateSquare() {
        for (int i = sx; i < sx + minSize; i++) {
            for (int j = sy; j < sy + minSize; j++) {
                if (maze[i][j] > 0) maze[i][j] -= 1;
            }
        }

        int[][] tmp = new int[n][n];
        int indexY = sy;
        for (int i = sx; i < sx + minSize; i++) {
            int indexX = sx + minSize - 1;
            for (int j = sy; j < sy + minSize; j++) {
                tmp[i][j] = maze[indexX--][indexY];
            }
            indexY += 1;
        }

        for (int i = sx; i < sx + minSize; i++) {
            for (int j = sy; j < sy + minSize; j++) {
                maze[i][j] = tmp[i][j];
            }
        }
    }

    private static void findMinSquare() {
        for (int size = 2; size <= n; size++) {
            for (int i = 0; i <= n - size; i++) {
                for (int j = 0; j <= n - size; j++) {
                    int x = i + size - 1;
                    int y = j + size - 1;

                    if (!(i <= exit.x && exit.x <= x && j <= exit.y && exit.y <= y)) continue;

                    boolean isInParticipant = false;
                    for (int k = 0; k < m; k++) {
                        if (i <= participants[k].x && participants[k].x <= x && j <= participants[k].y && participants[k].y <= y) {
                            if (!participants[k].isEscaped) isInParticipant = true;
                        }
                    }

                    if (isInParticipant) {
                        sx = i;
                        sy = j;
                        minSize = size;

                        return;
                    }
                }
            }
        }
    }

    private static void moveAllParticipants() {
        for (int i = 0; i < m; i++) {
            if (participants[i].isEscaped) continue;

            int x = participants[i].x;
            int y = participants[i].y;

            if (participants[i].x != exit.x) {
                if (exit.x > x) x += 1;
                else x -= 1;

                if (maze[x][y] == 0) {
                    participants[i].x = x;
                    answer += 1;

                    if (exit.x == x && exit.y == y) participants[i].isEscaped = true;

                    continue;
                }
            }

            if (participants[i].y != exit.y) {
                if (exit.y > y) y += 1;
                else y -= 1;

                if (maze[x][y] == 0) {
                    participants[i].y = y;
                    answer += 1;

                    if (exit.x == x && exit.y == y) participants[i].isEscaped = true;

                    continue;
                }
            }
        }
    }
}
