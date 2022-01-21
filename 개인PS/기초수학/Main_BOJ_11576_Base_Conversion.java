package 기초수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_11576_Base_Conversion {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(stringTokenizer.nextToken());
        int B = Integer.parseInt(stringTokenizer.nextToken());

        int m = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());
        int dec = 0;
        for(int i = 0  ; i < m ; i++){
            int x = Integer.parseInt(stringTokenizer.nextToken());
            dec += Math.pow(A, m - i - 1) * x;
        }

        List<Integer> ans = new ArrayList<>();
        while(dec != 0){
            ans.add(dec % B);
            dec /= B;
        }

        for(int i = ans.size()-1; i >= 0 ; i--){
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();
    }
}
