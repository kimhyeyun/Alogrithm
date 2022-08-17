public class L1_문자열을_정수로_바꾸기 {
    public int solution(String s) {
        boolean isMinus = false;
        int answer = 0;

        if(s.charAt(0) == '-') isMinus = true;

        if (isMinus) {
            s = s.substring(1);
            answer = Integer.parseInt(s);
            answer *= -1;
        } else {
            if (s.charAt(0) == '+') {
                s = s.substring(1);
            }
            answer = Integer.parseInt(s);
        }

        return answer;
    }

    public static void main(String[] args) {

    }
}
