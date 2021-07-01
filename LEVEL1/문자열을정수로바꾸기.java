public class 문자열을정수로바꾸기 {
    public static void main(String[] args) {
        System.out.println(solution("1234"));
    }

    public static int solution(String s) {
        int answer = 0;
        int num = 0;

        boolean flag = false;
        boolean minus = false;
        if(s.charAt(0) == '-' || s.charAt(0) == '+'){
            flag = true;
            if(s.charAt(0) == '-')
                minus = true;
        }

        int idx = s.length()-1;
        int i = 0;
        while(idx >= 0){
            if('0' <= s.charAt(idx) && s.charAt(idx) <= '9'){
                answer += ((s.charAt(idx) - '0') * Math.pow(10,i ));
                i++;
            }
            else
                break;

            idx--;
        }

        if(minus)
            answer *= -1;

        return answer;
    }
}
