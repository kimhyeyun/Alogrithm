package 기초수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_1934_최소공배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(stringTokenizer.nextToken());
            int B = Integer.parseInt(stringTokenizer.nextToken());

            sb.append(lcm(A, B)).append("\n");
        }
        System.out.println(sb);
    }

    private static int lcm(int a, int b) {
        int g = gcd(a, b);
        return (a * b) / g;
    }

    private static int gcd(int a, int b) {
        int m = a % b;

        if(m == 0)
            return b;
        else
            return gcd(b, m);
    }
}
