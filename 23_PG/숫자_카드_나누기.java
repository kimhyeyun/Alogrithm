import java.util.*;

public class 숫자_카드_나누기 {

    private List<Integer> divisors;

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        int satisfy = 0;

        satisfy = findNumSatisfiedConditions(arrayA, arrayB);
        answer = Math.max(answer, satisfy);

        satisfy = findNumSatisfiedConditions(arrayB, arrayA);
        answer = Math.max(answer, satisfy);

        return answer;
    }

    private int findNumSatisfiedConditions(int[] divArr, int[] nonDivArr) {

        Arrays.sort(divArr);
        findDivisors(divArr[0]);

        Collections.sort(divisors, Collections.reverseOrder());

        for (int divisior : divisors) {
            if(canDividedAllElement(divisior, divArr) && cantDividedAllElement(divisior, nonDivArr)) return divisior;
        }

        return 0;
    }

    private boolean cantDividedAllElement(int divisior, int[] arr) {
        for (int a : arr) {
            if(a % divisior == 0) return false;
        }
        return true;
    }

    private boolean canDividedAllElement(int divisior, int[] arr) {
        for (int a : arr) {
            if(a % divisior != 0) return false;
        }
        return true;
    }

    private void findDivisors(int num) {
        divisors = new ArrayList<>();

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                divisors.add(i);
                divisors.add(num / i);
            }
        }

        divisors.add(num);
    }

}
