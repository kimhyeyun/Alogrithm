import java.util.HashSet;
import java.util.LinkedList;

public class 수식최대화 {
    static long max;
    static LinkedList<Long> numList;
    static LinkedList<Character> opList, opSetList;
    static HashSet<Character> opSet;

    public static long solution(String expression) {

        max = Long.MIN_VALUE;
        numList = new LinkedList<>();
        opList = new LinkedList<>();
        opSetList = new LinkedList<>();
        opSet = new HashSet<>();

        // 연산자와 숫자로 나누기
        splitExp(expression);

        // 연산자 우선순위를 순열로 구하기
        operatorPerm(new LinkedList<Character>(), new boolean[opSetList.size()]);

        return max;
        
    }

    private static void operatorPerm(LinkedList<Character> list, boolean[] picked) {

        if(list.size() == opSetList.size()){
            // 계산 시작
            LinkedList<Long> copyNumList = new LinkedList<>(numList);
            LinkedList<Character> copyOpList = new LinkedList<>(opList);

            for(char nowOp : list){
                for(int i = 0; i < copyOpList.size();i++){
                    if(copyOpList.get(i) != nowOp){
                        continue;
                    }

                    long num1 = copyNumList.get(i);
                    long num2 = copyNumList.get(i+1);

                    copyNumList.remove(i+1);
                    copyNumList.remove(i);

                    copyOpList.remove(i);

                    copyNumList.add(i, calcNumber(num1, num2, nowOp));

                    i--;
                }
            }

            max = Math.max(max, Math.abs(copyNumList.get(0)));
            
            return;

        }

        for(int i = 0;i < opSetList.size();i++){
            if(!picked[i]){
                picked[i] = true;
                list.add(opSetList.get(i));

                operatorPerm(list, picked);

                picked[i] = false;
                list.removeLast();
            }
        }
    }
    
    private static Long calcNumber(long num1, long num2, char op) {
        long result = 0;

        switch(op){
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

    private static void splitExp(String exp) {
        char[] arr = exp.toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0;i < arr.length;i++){
            if(arr[i] == '+' || arr[i] == '-' || arr[i] == '*'){
                long number = Long.valueOf(sb.toString());

                numList.add(number);
                opList.add(arr[i]);
                opSet.add(arr[i]);

                sb = new StringBuilder();
            }
            else{
                sb.append(arr[i]);
            }
        }

        numList.add(Long.valueOf(sb.toString()));

        opSetList = new LinkedList<>(opSet);
    }
    public static void main(String[] args) {
        System.out.println(solution("100-200*300-500+20"));
    }


}
