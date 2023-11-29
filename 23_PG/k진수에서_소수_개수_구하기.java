public class k진수에서_소수_개수_구하기 {
    public int solution(int n, int k) {
        int answer = 0;

        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.append(n % k);
            n /= k;
        }

        String s = sb.reverse().toString();
        String[] split = s.split("0+");

        for (String num : split) {
            long number = Long.parseLong(num);

            if(getPrime(number)) answer += 1;
        }

        return answer;
    }

    private boolean getPrime(long number) {
        if(number == 1) return false;
        if(number == 2) return true;

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if(number % i == 0) return false;
        }
        return true;
    }
}
