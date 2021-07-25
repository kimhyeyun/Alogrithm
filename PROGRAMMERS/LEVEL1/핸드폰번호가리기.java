public class 핸드폰번호가리기 {
    public static void main(String[] args) {
        System.out.println(solution("01033334444"));
        System.out.println(solution("027778888"));
    }
    

    public static String solution(String phone_number) {
        String answer = "";

        int l = phone_number.length() - 4;

        for(int i = 0; i<l;i++){
            answer += '*';
        }

        for(int i = l;i<phone_number.length();i++){
            answer+=phone_number.charAt(i);
        }

        return answer;
    }
}
