public class L1_약수의_합 {
    public int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                answer += i;
                answer += (n / i);

                if(i == (n/i)) answer -= i;
            }
        }
        return answer;
    }
}
