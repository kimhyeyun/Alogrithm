import java.util.*;

public class 수식_최대화 {

    List<Long> numbers;
    List<Character> operators, opPerms;
    Long answer;

    public long solution(String expression) {

        answer = Long.MIN_VALUE;
        numbers = new ArrayList<>();
        operators = new ArrayList<>();

        splitExpr(expression);
        permutation(new LinkedList<Character>(), new boolean[opPerms.size()]);

        return answer;
    }

    private void permutation(LinkedList<Character> list, boolean[] isPicked) {
        if (list.size() == opPerms.size()) {
            List<Long> tmpNumbers = new ArrayList<>(numbers);
            List<Character> tmpOp = new ArrayList<>(operators);

            for (char op : list) {
                for (int i = 0; i < tmpOp.size(); i++) {
                    if(tmpOp.get(i) != op) continue;

                    long num1 = tmpNumbers.get(i);
                    long num2 = tmpNumbers.get(i + 1);

                    tmpNumbers.remove(i + 1);
                    tmpNumbers.remove(i);

                    tmpOp.remove(i);

                    tmpNumbers.add(i, calculate(num1, num2, op));

                    i -= 1;
                }
            }
            answer = Math.max(answer, Math.abs(tmpNumbers.get(0)));
            return;
        }

        for (int i = 0; i < opPerms.size(); i++) {
            if(isPicked[i]) continue;

            isPicked[i] = true;
            list.add(opPerms.get(i));
            permutation(list, isPicked);
            isPicked[i] = false;
            list.removeLast();
        }
    }

    private Long calculate(long num1, long num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
        }
        return num1 * num2;
    }

    private void splitExpr(String expression) {
        StringBuilder sb = new StringBuilder();
        Set<Character> op = new HashSet<>();

        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-' || c == '*') {
                numbers.add(Long.parseLong(sb.toString()));
                op.add(c);
                operators.add(c);

                sb = new StringBuilder();
            } else sb.append(c);
        }

        opPerms = new ArrayList<>(op);
        numbers.add(Long.parseLong(sb.toString()));
    }
}
