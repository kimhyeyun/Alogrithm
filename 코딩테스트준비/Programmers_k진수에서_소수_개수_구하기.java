import javax.swing.*;
import java.util.Arrays;

public class Programmers_k진수에서_소수_개수_구하기 {

    public static int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();

        while (n != 0) {
            sb.append(n % k);
            n /= k;
        }

        String str = sb.reverse().toString();
        String[] numbers = str.split("0+");
        for (String num : numbers) {
            long prime = Long.parseLong(num);

            if(getPrime(prime)) answer += 1;
        }




        return answer;
    }

    private static boolean getPrime(Long primeNum) {
        if(primeNum == 1) return false;
        if(primeNum == 2) return true;
        for (int i = 2; i < Math.sqrt(primeNum) + 1; i++) {
            if (primeNum % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution(110011, 10));
    }
}
