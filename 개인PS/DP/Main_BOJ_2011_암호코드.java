package DP;

import java.util.Scanner;

public class Main_BOJ_2011_암호코드 {
    static final int mod = 1000000;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String encryptionStr = scanner.next();

        int len = encryptionStr.length();
        int[] encryption = new int[len+1];
        int[] DP = new int[len+1];

        if(len == 0 || encryptionStr.charAt(0) == '0'){
            System.out.println(0);
            return;
        }

        for(int i = 1; i <= len ;i++){
            encryption[i] = encryptionStr.charAt(i-1) - '0';
        }

        DP[0] = 1;
        for(int i = 1; i <= len ; i++){
            if(0 < encryption[i] && encryption[i] < 10)
                DP[i] = (DP[i-1] + DP[i]) % mod;

            if(i == 1)  continue;

            int tmp = encryption[i-1]*10 + encryption[i];
            if(9 < tmp && tmp < 27)
                DP[i] = (DP[i] + DP[i-2]) % mod;
        }

        System.out.println(DP[len]);


    }
}
