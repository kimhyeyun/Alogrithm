package 기초수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1373_2진수_8진수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();

        int j = 0;
        int sum = 0;
        for(int i = input.length()-1 ; i >= 0 ; i--){
            int len = input.length() - i - 1;
            if((i != input.length() - 1 && len % 3 == 0)) {
                sb.append(sum);
                j = 0;
                sum = 0;
            }

            sum += Math.pow(2, j) * (input.charAt(i) - '0');
            j++;
        }
        sb.append(sum);

        System.out.println(sb.reverse());

    }
}
