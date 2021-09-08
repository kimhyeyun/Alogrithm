import java.util.Scanner;

public class Main_5568_BOJ_123더하기5{
    static int mod = 1000000009;
    static long[][] DP = new long[100001][4];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();

        DP[1][1] = 1;
        DP[1][2] = 0;
        DP[1][3] = 0;
        DP[2][1] = 0;
        DP[2][2] = 1;
        DP[2][3] = 0;
        DP[3][1] = 1;
        DP[3][2] = 1;
        DP[3][3] = 1;

        for(int i = 4; i < 100001; i++){
            DP[i][1] = (DP[i-1][2] + DP[i-1][3]) % mod;
            DP[i][2] = (DP[i-2][1] + DP[i-2][3]) % mod;
            DP[i][3] = (DP[i-3][1] + DP[i-3][2]) % mod;
        }

        while(n-- > 0){
            int x = sc.nextInt();
            System.out.println((DP[x][1] + DP[x][2] + DP[x][3]) % mod);
        }
    }
}