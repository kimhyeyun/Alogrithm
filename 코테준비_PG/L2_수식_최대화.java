import java.util.*;

public class L2_수식_최대화 {
    static long answer;
    static List<Long> numbers;
    static List<Character> operators, operatorPerm;
    public static long solution(String expression) {
        answer = Long.MIN_VALUE;

        numbers = new ArrayList<>();
        operators = new ArrayList<>();

        splitExp(expression);

        opPerm(new LinkedList<Character>(), new boolean[operatorPerm.size()]);

        return answer;
    }

    private static void opPerm(LinkedList<Character> list, boolean[] isPicked) {
        if (list.size() == operatorPerm.size()) {
            List<Long> tmpNumbers = new ArrayList<>(numbers);
            List<Character> tmpOperators = new ArrayList<>(operators);

            for (char op : list) {
                for (int i = 0; i < tmpOperators.size(); i++) {
                    if(tmpOperators.get(i) != op) continue;

                    long num1 = tmpNumbers.get(i);
                    long num2 = tmpNumbers.get(i + 1);

                    tmpNumbers.remove(i + 1);
                    tmpNumbers.remove(i);

                    tmpOperators.remove(i);

                    tmpNumbers.add(i, cal(num1, num2, op));

                    i -= 1;
                }
            }
            answer = Math.max(answer, Math.abs(tmpNumbers.get(0)));
            return;
        }

        for (int i = 0; i < operatorPerm.size(); i++) {
            if(isPicked[i]) continue;

            isPicked[i] = true;
            list.add(operatorPerm.get(i));
            opPerm(list, isPicked);
            isPicked[i] = false;
            list.removeLast();
        }
    }

    private static Long cal(long num1, long num2, char op) {
        long result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
        }
        return result;
    }

    private static void splitExp(String expression) {
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();

        for (char c : expression.toCharArray()) {
            if (c == '*' || c == '-' || c == '+') {
                numbers.add(Long.valueOf(sb.toString()));
                set.add(c);
                operators.add(c);

                sb = new StringBuilder();
            } else sb.append(c);
        }

        numbers.add(Long.valueOf(sb.toString()));
        operatorPerm = new ArrayList<>(set);
    }

    public static void main(String[] args) {
        System.out.println(solution("50*6-3*2"));
    }
}
