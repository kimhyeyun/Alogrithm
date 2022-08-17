public class L1_이상한_문자_만들기 {
    public static String solution(String s) {
        String answer = "";
        int idx = 0;
        s = s.toUpperCase();

        for (char c : s.toCharArray()) {
            if(c == ' '){
                answer += c;
                idx = 0;
                continue;
            }

            if (idx % 2 == 0) {
                answer += c;
            } else {
                answer += (char)(c + 32);
            }

            idx += 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("try hello world"));
    }
}
