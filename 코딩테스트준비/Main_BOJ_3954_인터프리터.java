import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BOJ_3954_인터프리터 {
    static int testCase, arrSize, codeLen, inputLen, arrayPointer, inputPointer, codePointer;
    static int[] array, bracket;
    static String input, code;
    static Stack<Integer> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer;

        testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            arrSize = Integer.parseInt(stringTokenizer.nextToken());
            codeLen = Integer.parseInt(stringTokenizer.nextToken());
            inputLen = Integer.parseInt(stringTokenizer.nextToken());

            array = new int[arrSize];
            arrayPointer = 0;
            inputPointer = 0;

            code = br.readLine();
            input = br.readLine();

            stack = new Stack<>();
            bracket = new int[codeLen];
            for (int i = 0; i < codeLen; i++) {
                if(code.charAt(i) == '[') stack.push(i);
                else if (code.charAt(i) == ']') {
                    int tmp = stack.peek();
                    bracket[i] = tmp;
                    bracket[tmp] = i;
                    stack.pop();
                }
            }


            int cnt = 0;
            codePointer = 0;
            while (cnt++ <= 50000000 && codePointer < codeLen) {
                goInterpret();
            }

            if (codePointer == codeLen) {
                sb.append("Terminates\n");
            } else {
                int maxP = codePointer, minP = codePointer;

                while (cnt-- > 0) {
                    goInterpret();
                    maxP = Math.max(maxP, codePointer);
                    minP = Math.min(minP, codePointer);
                }
                sb.append("Loops " + (minP - 1) + " " + maxP).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void goInterpret() {
        char c = code.charAt(codePointer);

        switch (c) {
            case '+':
                array[arrayPointer] = (array[arrayPointer] + 1 > 255) ? 0 : array[arrayPointer] + 1;
                break;
            case '-':
                array[arrayPointer] = (array[arrayPointer] - 1 < 0) ? 255 : array[arrayPointer] - 1;
                break;
            case '<':
                arrayPointer = arrayPointer - 1 == -1 ? arrSize - 1 : arrayPointer - 1;
                break;
            case '>':
                arrayPointer = arrayPointer + 1 == arrSize ? 0 : arrayPointer + 1;
                break;
            case '[':
                if (array[arrayPointer] == 0) {
                    codePointer = bracket[codePointer];
                }
                break;
            case ']':
                if (array[arrayPointer] != 0) {
                    codePointer = bracket[codePointer];
                }
                break;
            case '.':
                break;
            case ',':
                array[arrayPointer] = (inputPointer == inputLen) ? 255 : input.charAt(inputPointer++);
        }

        codePointer += 1;
    }
}
