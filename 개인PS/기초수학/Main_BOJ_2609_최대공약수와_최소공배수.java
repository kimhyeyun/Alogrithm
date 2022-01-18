package 기초수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2609_최대공약수와_최소공배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(stringTokenizer.nextToken());
        int B = Integer.parseInt(stringTokenizer.nextToken());

        System.out.println(gcd(A, B));
        System.out.println(lcm(A, B));
    }

    private static int lcm(int a, int b) {
        int g = gcd(a, b);
        return (a*b)/g;
    }

    private static int gcd(int a, int b) {
        int g = a % b;
        if(g != 0)
            return gcd(b, g);
        else
            return b;
    }
}
