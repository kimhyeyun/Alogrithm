public class L1_핸드폰_번호_가리기 {
    public static String solution(String phone_number) {
        int len = phone_number.length() - 4;

        String answer = "";
        for (int i = 0; i < len; i++) answer += "*";

        answer += phone_number.substring(len);

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("027778888"));
    }
}
