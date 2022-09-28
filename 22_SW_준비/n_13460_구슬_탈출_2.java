import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n_13460_구슬_탈출_2 {
    static int N, M;
    static int[] red, blue, hole;
    static boolean[][][][] isVisited;
    static char[][] board;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        isVisited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if(board[i][j] == 'B') blue = new int[]{i, j};
                else if(board[i][j] == 'R') red = new int[]{i, j};
                else if(board[i][j] == 'O') hole = new int[]{i, j};
            }
        }

        System.out.println(solution());
    }

    private static int solution() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{red[0], red[1], blue[0], blue[1], 1});

        isVisited[red[0]][red[1]][blue[0]][blue[1]] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if(now[4] > 10) return -1;

            for (int d = 0; d < 4; d++) {
                int redX = now[0];
                int redY = now[1];
                int blueX = now[2];
                int blueY = now[3];
                int cnt = now[4];

                boolean isRedHole = false;
                boolean isBlueHole = false;

                while(true){
                    if(board[redX+dx[d]][redY +dy[d]] == '#') break;

                    redX += dx[d];
                    redY += dy[d];

                    if (redX == hole[0] && redY == hole[1]) {
                        isRedHole = true;
                        break;
                    }
                }

                while(true){
                    if(board[blueX+dx[d]][blueY+dy[d]] == '#') break;

                    blueX += dx[d];
                    blueY += dy[d];

                    if (blueX == hole[0] && blueY == hole[1]) {
                        isBlueHole = true;
                        break;
                    }
                }


                if(isBlueHole) continue;
                if(isRedHole) return cnt;

                if (redX == blueX && redY == blueY) {
                    if(d == 0) {
                        //up
                        if(now[0] > now[2]) redX += 1;
                        else blueX += 1;
                    } else if (d == 1) {
                        //left
                        if(now[1] > now[3]) redY += 1;
                        else blueY += 1;
                    } else if (d == 2) {
                        //right
                        if(now[1] > now[3]) blueY -= 1;
                        else redY -= 1;
                    } else {
                        //down
                        if(now[0] > now[2]) blueX -= 1;
                        else redX -= 1;
                    }
                }

                if(isVisited[redX][redY][blueX][blueY]) continue;

                isVisited[redX][redY][blueX][blueY] = true;
                q.add(new int[]{redX, redY, blueX, blueY, cnt + 1});


            }
        }
        return -1;
    }
}
