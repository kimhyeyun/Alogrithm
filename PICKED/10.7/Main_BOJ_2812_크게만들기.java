import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_2812_크게만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());
        int len = N - K;

        Stack<Character> stack = new Stack<>();

        String str = br.readLine();

        for(int i = 0; i < str.length() ; i++){
            if(!stack.empty()){
                while(!stack.empty() && K > 0 && stack.peek() < str.charAt(i)){
                    stack.pop();
                    K--;
                }
            }
            
            stack.push(str.charAt(i));
        }

        while(true){
            if(stack.size() == len)
                break;
            
            stack.pop();
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb.reverse().toString());
        
    }
}
