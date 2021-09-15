import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17070_파이프옮기기1 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N+1][N+1];
        int[][][] DP = new int[N+1][N+1][3];
        // 0 : 가로, 1 : 세로, 2 : 대각선

        for(int i = 1; i < N+1 ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++){
                house[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        DP[1][2][0] = 1;

        for(int i = 1 ; i <= N ; i++){
            for(int j = 3; j <= N ; j++){
                if(house[i][j] == 0){
                    // 가로
                    DP[i][j][0] = DP[i][j-1][0] + DP[i][j-1][2];
                    // 세로
                    DP[i][j][1] = DP[i-1][j][1] + DP[i-1][j][2];

                    // 대각선
                    if(house[i-1][j] != 1 && house[i][j-1] != 1){
                        DP[i][j][2] = DP[i-1][j-1][0] + DP[i-1][j-1][1] + DP[i-1][j-1][2];
                    }
                }
            }
        }

        System.out.println(DP[N][N][0] + DP[N][N][1] + DP[N][N][2]);
    }
    
}
