import java.util.Scanner;

public class 일이삼더하기4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        int[][] DP = new int[10001][4];
        DP[1][1] = 1;
        DP[1][2] = 0;
        DP[1][3] = 0;
        DP[2][1] = 1;
        DP[2][2] = 1;
        DP[2][3] = 0;
        DP[3][1] = 1;
        DP[3][2] = 1;
        DP[3][3] = 1;

        for(int i = 4; i < 10001 ; i++){
            DP[i][1] = 1;
            DP[i][2] = DP[i-2][1] + DP[i-2][2];
            DP[i][3] = DP[i-3][1] + DP[i-3][2] + DP[i-3][3];
        }

        while(testCase-- > 0){
            int N = sc.nextInt();

            System.out.println(DP[N][1] + DP[N][2] + DP[N][3]);            
        }
    }
    
}
