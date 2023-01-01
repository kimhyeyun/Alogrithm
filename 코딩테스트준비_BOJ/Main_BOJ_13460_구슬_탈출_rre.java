import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13460_구슬_탈출_rre {
    static int N, M;
    static int[] red, blue, hole;
    static char[][] board;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if(board[i][j] == 'R') {
                    red = new int[]{i, j};
                } else if (board[i][j] == 'B') {
                    blue = new int[]{i, j};
                } else if (board[i][j] == 'O') {
                    hole = new int[]{i, j};
                }
            }
        }

        System.out.println(BFS());
    }

    private static int BFS() {
        boolean[][][][] isVisited = new boolean[N][M][N][M];
        Queue<int[]> q = new LinkedList<>();

        isVisited[red[0]][red[1]][blue[0]][blue[1]] = true;
        q.add(new int[]{red[0], red[1], blue[0], blue[1], 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if (now[4] > 9) {
                return -1;
            }

            int rx = now[0];
            int ry = now[1];
            int bx = now[2];
            int by = now[3];

            for (int d = 0; d < 4; d++) {
                int nrx = rx;
                int nry = ry;
                int nbx = bx;
                int nby = by;

                boolean redGoal = false;
                boolean blueGoal = false;

                while (true) {
                    if (board[nrx + dx[d]][nry + dy[d]] == '#') {
                        break;
                    }

                    nrx += dx[d];
                    nry += dy[d];

                    if (nrx == hole[0] && nry == hole[1]) {
                        redGoal = true;
                        break;
                    }
                }

                while (true) {
                    if (board[nbx + dx[d]][nby + dy[d]] == '#') {
                        break;
                    }

                    nbx += dx[d];
                    nby += dy[d];

                    if (nbx == hole[0] && nby == hole[1]) {
                        blueGoal = true;
                        break;
                    }
                }

                if (blueGoal) {
                    continue;
                }
                if (redGoal) {
                    return now[4] + 1;
                }

                if (nrx == nbx && nry == nby) {
                    if (d == 0) {
                        //위
                        if (rx > bx) {
                            nrx += 1;
                        } else {
                            nbx += 1;
                        }
                    } else if (d == 1) {
                        //왼
                        if (ry > by) {
                            nry += 1;
                        } else {
                            nby += 1;
                        }
                    } else if (d == 2) {
                        //오
                        if (ry > by) {
                            nby -= 1;
                        } else {
                            nry -= 1;
                        }
                    } else {
                        //아래
                        if (rx > bx) {
                            nbx -= 1;
                        } else {
                            nrx -= 1;
                        }
                    }
                }

                isVisited[nrx][nry][nbx][nby] = true;
                q.add(new int[]{nrx, nry, nbx, nby, now[4] + 1});
            }
        }
        return -1;
    }
}
