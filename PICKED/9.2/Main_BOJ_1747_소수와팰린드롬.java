import java.util.Scanner;
import java.util.Stack;

public class Main_BOJ_1747_소수와팰린드롬 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        while(true){
            if(isPrime(N) && isPalindrome(N)){
                System.out.println(N);
                break;
            }
            N++;
        }
    }

    private static boolean isPalindrome(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(n);

        return sb.toString().equals(sb.reverse().toString());
        
    }

    private static boolean isPrime(int n) {
        if(n == 2){
            return true;
        }

        if(n % 2 == 0 || n == 1)
            return false;

        for(int i = 3; i <= Math.sqrt(n) ; i+=2){
            if(n % i == 0)
                return false;
        }

        return true;
    }
}
