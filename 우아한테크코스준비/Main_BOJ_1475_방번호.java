import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1475_방번호 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[10];

        while(N > 0){
            nums[N%10]++;
            N /= 10;
        }

        int ans = 0;
        for(int i = 0; i < 10 ; i++){
            if(i != 6 && i != 9)
                ans = Math.max(ans, nums[i]);
        }
        
        System.out.println(Math.max(ans, (nums[6]+nums[9]+1)/2));
    }
}
