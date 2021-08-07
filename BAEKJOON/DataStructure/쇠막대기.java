import java.util.Scanner;
import java.util.Stack;

public class 쇠막대기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cnt = 0;
        String str = sc.next();
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i <str.length(); i++){
            if(str.charAt(i) == '(')
                stack.push('(');
            else{
                stack.pop();

                if(str.charAt(i-1) == ')'){
                    cnt += 1;
                }
                else{
                    cnt += stack.size();
                }
            }
        }
        System.out.println(cnt);
    }
}
