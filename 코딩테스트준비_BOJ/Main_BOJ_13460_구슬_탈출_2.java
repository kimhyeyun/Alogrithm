import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13460_구슬_탈출_2 {

    static class Marble{
        int rx, ry, bx, by, cnt;

        public Marble(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }
    static int N, M;
    static char[][] map;
    static boolean[][][][] isVisited;
    static int holeX, holeY;
    static Marble red, blue;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0,};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[N][M];
        isVisited = new boolean[N][M][N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 'R') red = new Marble(i, j, 0, 0, 0);
                else if(map[i][j] == 'B') blue = new Marble(0, 0, i, j, 0);
                else if (map[i][j] == 'O') {
                    holeX = i;
                    holeY = j;
                }
            }
        }

        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<Marble> q = new LinkedList<>();
        q.add(new Marble(red.rx, red.ry, blue.bx, blue.by, 1));
        isVisited[red.rx][red.ry][blue.bx][blue.by] = true;

        while (!q.isEmpty()) {
            Marble now = q.poll();

            int rx = now.rx;
            int ry = now.ry;
            int bx = now.bx;
            int by = now.by;
            int nowCnt = now.cnt;

            if(nowCnt > 10) return -1;

            for (int d = 0; d < 4; d++) {
                int nrx = rx;
                int nry = ry;
                int nbx = bx;
                int nby = by;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                while (true) {
                    if(map[nrx + dx[d]][nry + dy[d]] == '#') break;

                    nrx += dx[d];
                    nry += dy[d];

                    if (nrx == holeX && nry == holeY) {
                        isRedHole = true;
                        break;
                    }
                }
                while (true) {
                    if(map[nbx + dx[d]][nby + dy[d]] == '#') break;

                    nbx += dx[d];
                    nby += dy[d];

                    if (nbx == holeX && nby == holeY) {
                        isBlueHole = true;
                        break;
                    }
                }

                if(isBlueHole) continue;
                if(isRedHole) return nowCnt;

                if (nrx == nbx && nry == nby) {
                    if (d == 0) {
                        if(rx < bx) nbx += 1;
                        else nrx += 1;
                    } else if (d == 1) {
                        if(ry < by) nby += 1;
                        else nry += 1;
                    } else if (d == 2) {
                        if (ry < by) nry -= 1;
                        else nby -= 1;
                    } else {
                        if(rx < bx) nrx -= 1;
                        else nbx -= 1;
                    }
                }

                if (!isVisited[nrx][nry][nbx][nby]) {
                    isVisited[nrx][nry][nbx][nby] = true;
                    q.add(new Marble(nrx, nry, nbx, nby, nowCnt + 1));
                    System.out.println(d+ " "+ nrx + " " + nry + " " + nbx + " " + nby);
                }
            }
        }
        return -1;
    }
}
