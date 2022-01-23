package 기초수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2004_조합_0의_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        long n = Long.parseLong(stringTokenizer.nextToken());
        long m = Long.parseLong(stringTokenizer.nextToken());

        long cntFive = fivePowerN(n) - fivePowerN(n - m) - fivePowerN(m);
        long cntTwo = twoPowerN(n) - twoPowerN(n - m) - twoPowerN(m);

        System.out.println(Math.min(cntFive, cntTwo));
    }

    private static long twoPowerN(long n) {
        int cnt = 0;

        while (n >= 2) {
            cnt += n / 2;
            n /= 2;
        }
        return cnt;
    }

    private static long fivePowerN(long n) {
        int cnt = 0;

        while(n >= 5){
            cnt += n / 5;
            n /= 5;
        }
        return cnt;
    }
}
