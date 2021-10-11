import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BOJ_2374_같은수로만들기 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Long> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        long max = 0;
        long ans = 0;
        for(int i = 0 ; i < N ; i++){
            long x = Long.parseLong(br.readLine());
            max = Math.max(max, x);

            if(stack.isEmpty())
                stack.push(x);
            else{
                if(stack.peek() < x){
                    ans += x - stack.pop();
                    stack.push(x);
                }
                else if(stack.peek() > x){
                    stack.pop();
                    stack.add(x);
                }
            }
        }

        while(!stack.isEmpty()){
            long num = stack.pop();
            ans += max - num;
        }

        System.out.println(ans);
    }
}
