package 기초수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_9613_GCD합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            stringTokenizer = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());

            List<Integer> list = new ArrayList<>();
            for(int i = 0 ; i < n ; i++){
                list.add(Integer.parseInt(stringTokenizer.nextToken()));
            }

            long sum = 0;
            for(int i = 0 ; i < n-1; i++){
                for(int j = i+1; j < n ; j++){
                    if(list.get(i) > list.get(j))
                        sum += gcd(list.get(i), list.get(j));
                    else
                        sum += gcd(list.get(j), list.get(i));
                }
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);

    }

    private static int gcd(int a, int b) {
        int g = a % b;
        if(g == 0)
            return b;
        return gcd(b, g);
    }
}
