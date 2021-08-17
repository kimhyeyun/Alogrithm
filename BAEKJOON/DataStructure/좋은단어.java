import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 좋은단어 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        while(N-- > 0){
            Stack<Character> stack = new Stack<>();
            String input = br.readLine();

            if(input.length() % 2 != 0){
                continue;
            }
            else{
                for(int i = 0; i < input.length(); i++){
                    if(!stack.isEmpty() && stack.peek() == input.charAt(i)){
                        stack.pop();
                    }
                    else{
                        stack.push(input.charAt(i));
                    }

                }
            }
            if(stack.size() == 0)
                answer++;
        }

        System.out.println(answer);

    }
}
