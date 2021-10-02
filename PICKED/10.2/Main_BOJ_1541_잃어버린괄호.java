import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ_1541_잃어버린괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] exp = br.readLine().split("-");
        int answer = 0;

        for(int i = 0 ; i < exp.length ; i++){
            int sum = 0;
            if(exp[i].contains("+")){
                String[] nums = exp[i].split("\\+");
                for(int j = 0 ; j < nums.length ; j++){
                    sum += Integer.parseInt(nums[j]);
                }
            }
            else
                sum += Integer.parseInt(exp[i]);

            answer = i == 0 ? sum : answer - sum;
        }

        System.out.println(answer);
    }
}
