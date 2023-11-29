import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_9935_문자열_폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String regx = br.readLine();

        int size = regx.length();
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            stack.push(c);

            if (stack.size() >= size) {
                boolean flag = true;

                for (int j = 0; j < size; j++) {
                    if (stack.get(stack.size() - size + j) != regx.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int j = 0; j < size; j++) stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c);

        System.out.println(sb.length() == 0 ? "FRULA" : sb);
    }
}
