package 기초수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_11005_진법_변환_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int input = Integer.parseInt(stringTokenizer.nextToken());
        int radix = Integer.parseInt(stringTokenizer.nextToken());

        StringBuilder sb = new StringBuilder();
        while(input > 0){
            int s = input % radix;
            input /= radix;

            if(s < 10)
                sb.append(s);
            else
                sb.append((char) (s + 55));
        }
        System.out.println(sb.reverse());

    }
}
