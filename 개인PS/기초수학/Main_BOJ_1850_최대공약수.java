package 기초수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1850_최대공약수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        long A = Long.parseLong(stringTokenizer.nextToken());
        long B = Long.parseLong(stringTokenizer.nextToken());

        long g;
        if(A > B)
            g = gcd(A, B);
        else
            g = gcd(B, A);

        for(int i = 0 ; i < g; i++)
            sb.append("1");

        System.out.println(sb);
    }

    private static long gcd(long a, long b) {
        long g = a % b;

        if(g == 0)
            return b;
        else
            return gcd(b, g);
    }
}
