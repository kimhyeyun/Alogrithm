import java.util.Arrays;
import java.util.Collections;

public class 카펫 {

    public static int[] solution(int brown, int yellow){
        int[] answer = new int[2];

        int sum = (brown - 4)/2 + 4; 
        int mul = brown + yellow;

        for(int a = 1; a <= mul; a++){
            if(mul%a == 0){
                int b = mul/a;
                if(a+b == sum){
                    answer[0] = Math.max(a, b);
                    answer[1] = Math.min(a, b);
                    break;
                }
            }
        }
        return answer;
    }
     public static void main(String[] args) {
         int[] ans = solution(10, 2);

         for(int a : ans){
             System.out.println(a);
         }
     }
}
