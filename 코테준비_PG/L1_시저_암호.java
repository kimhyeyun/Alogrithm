public class L1_시저_암호 {
    public static String solution(String s, int n) {
        String answer = "";

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                answer += c;
                continue;
            }
            for (int i = 1; i <= n; i++) {
                c += 1;
                if(c == '[') c = 'A';
                else if(c == '{') c = 'a';
            }
            answer += c;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("a B z", 4));
    }
}
