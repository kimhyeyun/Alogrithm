import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_2292_벌집{
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int ans = 1;
        int range = 1;
        
        while(true){
            if(N <= range)  break;
            
            range += 6 * ans;
            ans++;
        }

        System.out.println(ans);
    }
}