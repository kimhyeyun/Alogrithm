package 기초수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_2745_진법_변환 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        String input = stringTokenizer.nextToken();
        int radix = Integer.parseInt(stringTokenizer.nextToken());

        int sum = 0;
        if (radix == 10) {
            System.out.println(input);
        } else {
            for (int i = 0; i < input.length(); i++) {
                double p = Math.pow(radix, input.length() - i - 1);
                if ('A' <= input.charAt(i) && input.charAt(i) <= 'Z') {
                    sum += p * (int)(input.charAt(i) - 55);
                } else {
                    sum += p * (input.charAt(i) - '0');
                }
            }
            System.out.println(sum);
        }
    }
}
