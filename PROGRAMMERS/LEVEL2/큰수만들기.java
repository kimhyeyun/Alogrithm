import java.util.Stack;

public class 큰수만들기 {

    static Stack<Character> stack;

    public static String solution(String number, int k){
        String answer = "";
        stack = new Stack<>();

        int len = number.length() - k;

        for(int i = 0; i < number.length(); i++){
            char ch = number.charAt(i);
            int a = k;
            for(; (!stack.empty()) && a > 0; a--){
                char top = stack.peek();
                
                if(top >= ch)
                    break;
                
                stack.pop();
            }

            stack.push(ch);
        }

        while(stack.size() != len){
            stack.pop();
        }


        while(!stack.isEmpty()){
            char x = stack.peek();
            answer = x + answer;
            stack.pop();
        }

        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution("77777777", 3));
    }
}
