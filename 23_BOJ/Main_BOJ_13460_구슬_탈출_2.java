import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13460_구슬_탈출_2 {
    static int N, M, answer;
    static char[][] board;
    static int[] red, blue, hole;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answer = Integer.MAX_VALUE;
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if(board[i][j] == 'R') red = new int[]{i, j};
                else if(board[i][j] == 'B') blue = new int[]{i, j};
                else if(board[i][j] == 'O') hole = new int[]{i, j};
            }
        }

        BFS();

        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void BFS() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][][] isVisited = new boolean[N][M][N][M];

        q.add(new int[]{red[0], red[1], blue[0], blue[1], 0});
        isVisited[red[0]][red[1]][blue[0]][blue[1]] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int rx = now[0];
            int ry = now[1];
            int bx = now[2];
            int by = now[3];
            int dist = now[4];

            if(dist + 1 > 10) return;

            for (int d = 0; d < 4; d++) {
                int nrx = rx;
                int nry = ry;
                int nbx = bx;
                int nby = by;

                boolean isGoalRed = false;
                boolean isGoalBlue = false;

                // 빨간색 이동
                while (true) {
                    nrx += dx[d];
                    nry += dy[d];

                    if(nrx < 0 || nry < 0 || N <= nrx || M <= nry || board[nrx][nry] == '#'){
                        nrx -= dx[d];
                        nry -= dy[d];
                        break;
                    }

                    if(nrx == hole[0] && nry == hole[1]){
                        isGoalRed = true;
                        break;
                    }
                }

                // 파란색 이동
                while (true) {
                    nbx += dx[d];
                    nby += dy[d];

                    if(nbx < 0 || nby < 0 || N <= nbx || M <= nby || board[nbx][nby] == '#'){
                        nbx -= dx[d];
                        nby -= dy[d];
                        break;
                    }

                    if(nbx == hole[0] && nby == hole[1]){
                        isGoalBlue = true;
                        break;
                    }
                }

                if(isGoalBlue) continue;
                if(isGoalRed) answer = Math.min(answer, dist + 1);

                if (nrx == nbx && nry == nby) {
                    if (rx == bx) {
                        if (ry < by) {
                            if(d == 1) nby += 1;
                            else if(d == 2) nry -= 1;
                        }else{
                            if(d == 1) nry += 1;
                            else if(d == 2) nby -= 1;
                        }
                    }

                    if (ry == by) {
                        if(rx < bx){
                            if(d == 0) nbx += 1;
                            else if(d == 3) nrx -= 1;
                        }else{
                            if(d == 0) nrx += 1;
                            else if(d == 3) nbx -= 1;
                        }
                    }
                }

                if(isVisited[nrx][nry][nbx][nby]) continue;

                q.add(new int[]{nrx, nry, nbx, nby, dist + 1});
                isVisited[nrx][nry][nbx][nby] = true;
            }
        }
        return;
    }
}
