import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BOJ_10773_제로 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Stack<Integer> stack = new Stack<>();
        int K = Integer.parseInt(br.readLine());
        while(K-- > 0){
            int x = Integer.parseInt(br.readLine());
            if(x == 0)
                stack.pop();
            else
                stack.push(x);
        }

        int ans = 0;
        while(!stack.isEmpty()){
            ans += stack.pop();
        }

        System.out.println(ans);
    }
}
