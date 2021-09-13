import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17069_파이프옮기기2 {
    
    static int[][] house;
    static long[][][] DP;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        
        int n = Integer.parseInt(br.readLine());
        house = new int[n+1][n+1];
        // 0 : 가로, 1 : 세로, 2 : 대각선
        DP = new long[n+1][n+1][3];

        for(int i = 1 ; i <= n ; i++){
            stringTokenizer = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= n ; j++){
                house[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        DP[1][2][0] = 1;
        
        for(int i = 1; i <= n ; i++){
            for(int j = 3 ; j <= n ; j++){
                if(house[i][j] == 1)
                    continue;
                
                
                DP[i][j][0] = DP[i][j-1][0] + DP[i][j-1][2];
                DP[i][j][1] = DP[i-1][j][1] + DP[i-1][j][2];
                

                if(house[i-1][j] == 0 && house[i][j-1] == 0)
                    DP[i][j][2] = DP[i-1][j-1][0] + DP[i-1][j-1][1] + DP[i-1][j-1][2];
            }
        }

        System.out.println(DP[n][n][0] + DP[n][n][1] + DP[n][n][2]);
    }
}
