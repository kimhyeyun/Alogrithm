import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1748_수이어쓰기1 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        int len = N.length();

        if(len == 1)
            System.out.println(N);
        else{
            int ans = 0;
            for(int i = 1; i < len ; i++){
                ans += i * 9 * Math.pow(10, i-1);
            }

            int n = Integer.parseInt(N);
            int tmp = (int)Math.pow(10, len-1);

            ans += (len * (n - tmp + 1));

            System.out.println(ans);
        }

    }
}
