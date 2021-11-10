import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1110_더하기사이클 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int start = N;
        int ans = 0;

        while(true){
            N = (N%10) * 10 + (N/10 + N%10) % 10;
            ans++;

            if(start == N)
                break;
        }

        System.out.println(ans);
    }
}
