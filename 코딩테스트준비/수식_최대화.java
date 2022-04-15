import java.util.*;

public class 수식_최대화 {
    static long max;
    static List<Long> operand;
    static List<Character> operator, operatorSetList;
    static Set<Character> operatorSet;
    public static long solution(String expression) {
        max = Long.MIN_VALUE;

        operand = new ArrayList<>();
        operator = new ArrayList<>();
        operand = new ArrayList<>();
        operatorSet = new HashSet<>();

        splitExp(expression);

        operatorPerm(new LinkedList<Character>(), new boolean[operatorSetList.size()]);

        return max;
    }

    private static void operatorPerm(LinkedList<Character> list, boolean[] isPicked) {
        if (list.size() == operatorSetList.size()) {
            List<Long> tmpOperand = new ArrayList<>(operand);
            List<Character> tmpOperator = new ArrayList<>(operator);

            for (char op : list) {
                for (int i = 0; i < tmpOperator.size(); i++) {
                    if(tmpOperator.get(i) != op) continue;

                    long n1 = tmpOperand.get(i);
                    long n2 = tmpOperand.get(i + 1);

                    tmpOperand.remove(i + 1);
                    tmpOperand.remove(i);

                    tmpOperator.remove(i);

                    tmpOperand.add(i, cal(n1, n2, op));

                    i -= 1;
                }
            }

            max = max < Math.abs(tmpOperand.get(0)) ? Math.abs(tmpOperand.get(0)) : max;
            return;
        }

        for (int i = 0; i < operatorSetList.size(); i++) {
            if (!isPicked[i]) {
                isPicked[i] = true;
                list.add(operatorSetList.get(i));
                operatorPerm(list, isPicked);
                isPicked[i] = false;
                list.removeLast();
            }
        }
    }

    private static Long cal(long n1, long n2, char op) {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
        }
        return (long) 0;
    }

    private static void splitExp(String expression) {
        StringBuilder sb = new StringBuilder();

        for (char e : expression.toCharArray()) {
            if (e == '+' || e == '-' || e == '*') {
                long num = Long.valueOf(sb.toString());

                operand.add(num);
                operatorSet.add(e);
                operator.add(e);

                sb = new StringBuilder();
            } else {
                sb.append(e);
            }
        }

        operand.add(Long.valueOf(sb.toString()));
        operatorSetList = new ArrayList<>(operatorSet);

    }
}
