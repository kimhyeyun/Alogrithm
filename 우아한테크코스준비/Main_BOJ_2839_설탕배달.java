import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2839_설탕배달 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int fiveCnt = N/5;
        int threeCnt = 0;
        N %= 5;

        while(fiveCnt >= 0){
            if(N%3 == 0){
                threeCnt = N/3;
                N %= 3;
                break;
            }

            fiveCnt--;
            N += 5;
        }

        System.out.println(N == 0 ? fiveCnt + threeCnt : -1);
    }
}
