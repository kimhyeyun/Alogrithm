import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_1406_에디터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        int N = Integer.parseInt(br.readLine());

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        for (char c : str.toCharArray()) {
            left.push(c);
        }

        while (N-- > 0) {
            String command = br.readLine();

            switch (command.charAt(0)) {
                case 'L' -> {
                    if(!left.isEmpty()) right.push(left.pop());
                }
                case 'D' -> {
                    if(!right.isEmpty()) left.push(right.pop());
                }
                case 'P' -> {
                    char x = command.charAt(2);
                    left.push(x);
                }
                case 'B' -> {
                    if(!left.isEmpty()) left.pop();
                }
            }
        }

        while (!left.isEmpty()) sb.append(left.pop());
        sb.reverse();
        while (!right.isEmpty()) sb.append(right.pop());

        System.out.println(sb);

    }
}
