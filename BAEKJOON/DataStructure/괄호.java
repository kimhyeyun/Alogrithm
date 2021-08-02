import java.util.Scanner;
import java.util.Stack;

public class 괄호 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        for(int i = 0; i < T; i++){
            Stack<Character> stk = new Stack<>();
            String str = sc.next();
            boolean flag = true;

            for(int j = 0; j < str.length();j++){
                if(str.charAt(j) == '('){
                    stk.add('(');
                }
                
                else if(str.charAt(j) == ')'){
                    if(stk.empty()){
                        sb.append("NO\n");
                        flag = false;
                        break;
                    }
                    else{
                        stk.pop();
                    }
                }
            } 

            if(flag && stk.empty()){
                sb.append("YES\n");
            }
            else if(flag && !stk.empty())    
                sb.append("NO\n");
        }

        System.out.println(sb);
    }
}
