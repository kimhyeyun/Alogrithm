import java.util.Scanner;

public class Main_BOJ_17175_피보나치는지겨워 {
    static int mod = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[51];

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= 50 ; i++){
            dp[i] = (dp[i-1] + dp[i-2] + 1) % mod;
        }
        
        System.out.println(dp[n]%mod);
    }
}
