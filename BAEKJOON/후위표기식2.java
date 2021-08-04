import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class 후위표기식2 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String postfix = br.readLine();

        Double[] num = new Double[N];
        for(int i = 0; i < N; i++){
            num[i] = Double.parseDouble(br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        
        for(int i = 0; i < postfix.length();i++){
            if('A' <= postfix.charAt(i) && postfix.charAt(i) <= 'Z'){
                stack.push(num[postfix.charAt(i) - 'A']);
            }
            else if(postfix.charAt(i) == '+'){
                double b = stack.pop();
                double a = stack.pop();

                stack.push(a+b);
            }
            else if(postfix.charAt(i) == '-'){
                double b = stack.pop();
                double a = stack.pop();

                stack.push(a-b);
            }

            else if(postfix.charAt(i) == '*'){
                double b = stack.pop();
                double a = stack.pop();

                stack.push(a*b);
            }

            else if(postfix.charAt(i) == '/'){
                double b = stack.pop();
                double a = stack.pop();

                stack.push(a/b);
            }
        }
        System.out.println(String.format("%.2f", stack.peek()));
    }
}
