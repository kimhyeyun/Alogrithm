import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 스택수열 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int start = 0;
        StringTokenizer st;

        while(N-- > 0){
            st = new StringTokenizer(br.readLine(), " ");

            int value = Integer.parseInt(st.nextToken());

            if(value > start){
                for(int i = start + 1; i <= value; i++){
                    stack.push(i);
                    sb.append("+\n");
                }
                start = value;
            }

            else if(stack.peek() != value){
                System.out.println("NO");
                return;
            }

            stack.pop();
            sb.append("-\n");
        }

        System.out.println(sb);
    }
}
