public class L2_124_나라의_숫자 {
    public String solution(int n) {
        String answer = "";

        while (n > 0) {
            if (n % 3 == 0) {
                answer += "4";
                n = n / 3 - 1;
            } else {
                answer = String.valueOf(n % 3) + answer;
                n /= 3;
            }
        }
        return answer;
    }
}
