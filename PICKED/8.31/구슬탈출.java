import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구슬탈출 {
    public static class marble{
        int x;
        int y;

        marble(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static marble red, blue;
    static int N, M;
    static int answer = 0;
    static char[][] board;
    // 위, 왼, 오, 아래
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        board = new char[N][M];

        for(int i = 0 ; i < N ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            board[i] = stringTokenizer.nextToken().toCharArray();

            for(int j = 0 ; j < M; j++){
                if(board[i][j] == 'R')
                    red = new marble(i, j);
                if(board[i][j] == 'B')
                    blue = new marble(i, j);
            }
        }

        for(int i = 0 ; i < 4; i++){
            DFS(red, blue, i, 1);
        }

        System.out.println(answer);
    }


    private static void DFS(marble red, marble blue, int dir, int cnt) {

        if(cnt > 10)
            return;

        int redX = red.x;
        int redY = red.y;
        int blueX = blue.x;
        int blueY = blue.y;

        boolean exit = false;

        while(0 <= redX && redX < N && 0 <= redY && redY < M){
            redX += dx[dir];
            redY += dy[dir];

            // 벽
            if(board[redX][redY] == '#'){
                redX -= dx[dir];
                redY -= dy[dir];
                break;
            }

            // 구멍
            if(board[redX][redY] == 'O'){
                exit = true;
                break;
            }
        }

        while(0 <= blueX && blueX < N && 0 <= blueY && blueY < M){
            blueX += dx[dir];
            blueY += dy[dir];

            // 벽
            if(board[blueX][blueY] == '#'){
                blueX -= dx[dir];
                blueY -= dy[dir];
                break;
            }

            // 구멍 -> 파란색이빠지면 바로 종료
            if(board[blueX][blueY] == 'O'){
                return;
            }
        }

        // 빨간공만 구멍으로 탈출함
        if(exit){
            answer = 1;
            return;
        }

        if(redX == blueX && redY == blueY){
            // 같은곳에 위치

            // 1. 방향이 위쪽
            if(dir == 0){
                if(red.x > blue.x)
                    redX++;
                else
                    blueX++;
            }
            // 2. 방향이 왼쪽
            else if(dir == 1){
                if(red.y < blue.y)
                    blueY++;
                else
                    redY++;
            }
            // 3. 방향이 오른쪽
            else if(dir == 2){
                if(red.y < blue.y)
                    redY--;
                else
                    blueY--;
            }
            // 4. 방향이 아래쪽
            else{
                if(red.x < blue.x)
                    redX--;
                else
                    blueX--;
            }
        }

        red = new marble(redX, redY);
        blue = new marble(blueX, blueY);

        for(int i = 0 ; i < 4; i++){
            DFS(red, blue, i, cnt+1);
        }
    }
}
