import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BOJ_1467_수_지우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String num = br.readLine();
        String erase = br.readLine();

        int[] countOfNum = new int[10];
        int[] countOfErase = new int[10];

        for (char c : num.toCharArray()) {
            countOfNum[c - '0'] += 1;
        }
        for (char c : erase.toCharArray()) {
            countOfErase[c - '0'] += 1;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);

        for (char c : num.toCharArray()) {
            int n = c - '0';

            if (countOfNum[n] == countOfErase[n]) {
                countOfNum[n] -= 1;
                countOfErase[n] -= 1;
                continue;
            }

            while (stack.peek() < n && 1 <= countOfErase[stack.peek()]) countOfErase[stack.pop()] -= 1;
            stack.push(n);
            countOfNum[n] -= 1;
        }

        while (1 < stack.size()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.reverse().toString());

    }
}
