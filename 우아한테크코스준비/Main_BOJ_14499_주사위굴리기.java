import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_14499_주사위굴리기 {
    static int[][] map;
    static int N, M, K, x, y;
    static int[] sero, garo;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        x = Integer.parseInt(stringTokenizer.nextToken());
        y = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][M];
        for(int i = 0 ; i < N ;i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ;j++){
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        // 1 -> 윗면, 3 -> 아랫면
        garo = new int[4];
        sero = new int[4];

        stringTokenizer = new StringTokenizer(br.readLine());
        while(K-- > 0){
            int dir = Integer.parseInt(stringTokenizer.nextToken());
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || N <= nx || ny < 0 || M <= ny)  continue;

            x = nx; 
            y = ny;
            if(dir == 1 || dir == 2){
                // 동 or 서
                if(dir == 1){
                    // 동쪽으로 굴리기
                    int tmp = garo[3];
                    for(int i = 3 ; i > 0 ; i--)
                        garo[i] = garo[i-1];

                    garo[0] = tmp;  
                }
                else{
                    // 서쪽으로 굴리기
                    int tmp = garo[0];
                    for(int i = 0 ; i < 3 ; i++)
                        garo[i] = garo[i+1];

                    garo[3] = tmp;
                }

                copyNum("garo");
            }
            else{
                if(dir == 3){
                    // 북쪽으로 굴리기
                    int tmp = sero[0];
                    for(int i = 0 ; i < 3; i++)
                        sero[i] = sero[i+1];
                    sero[3] = tmp;
                }
                else{
                    // 남쪽으로 굴리기
                    int tmp = sero[3];
                    for(int i = 3; i > 0 ; i--)
                        sero[i] = sero[i-1];
                    sero[0] = tmp;
                }
                copyNum("sero");
            }
        }


    }
    private static void copyNum(String str) {
        if(str.equals("garo")){
            if(map[x][y] != 0){
                garo[3] = map[x][y];
                map[x][y] = 0;
            }
            else
                map[x][y] = garo[3];

            sero[3] = garo[3];
            sero[1] = garo[1];
        }
        else{
            if(map[x][y] != 0){
                sero[3] = map[x][y];
                map[x][y] = 0;
            }
            else
                map[x][y] = sero[3];

            garo[1] = sero[1];
            garo[3] = sero[3];
        }

        System.out.println(garo[1]);
    }
}
