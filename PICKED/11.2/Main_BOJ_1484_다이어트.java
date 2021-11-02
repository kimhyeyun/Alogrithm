import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_BOJ_1484_다이어트 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());

        int start = 1;
        int end = 1;
        boolean flag = false;

        while(true){
            long diff = (long)(Math.pow(start, 2)) - (long)(Math.pow(end, 2));
            if(start - end == 1 && diff > G)    break;
            if(diff >= G)
                end++;
            else
                start++;

            if(diff == G){
                System.out.println(start);
                flag = true;
            }
        }

        if(!flag)
            System.out.println(-1);
    }
}
