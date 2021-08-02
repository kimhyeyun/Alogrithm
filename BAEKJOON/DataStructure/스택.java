import java.util.Scanner;
import java.util.Stack;

public class 스택 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        
        int N = sc.nextInt();

        for(int i = 0; i < N; i++){
            String str = sc.next();

            if(str.equals("push")){
                int x = sc.nextInt();
                stk.add(x);
            }

            else if(str.equals("pop")){
                if(stk.isEmpty()){
                    sb.append("-1\n");
                }
                else
                    sb.append(stk.pop() + "\n");
            }

            else if(str.equals("size")){
                sb.append(stk.size() + "\n");
            }

            else if(str.equals("empty")){
                if(stk.empty())
                    sb.append("1\n");
                else
                    sb.append("0\n");
                
            }

            else if(str.equals("top")){
                if(stk.empty())
                    sb.append("-1\n");
                else   
                    sb.append(stk.peek() +"\n");
            }
        }

        System.out.println(sb);
    }
}
