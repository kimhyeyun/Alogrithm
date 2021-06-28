public class 문자열다루기기본{
    public static void main(String[] args) {
        String s = "1234";
        System.out.println(solution(s));
    }

    public static boolean solution(String s) {
        boolean answer = true;

        if(s.length() == 4 || s.length() == 6){
            for(int i=0;i<s.length();i++){
                if(0 <= s.charAt(i) - '0' && s.charAt(i) - '0' <= 9){
                    continue;
                }
                else{
                    answer = false;
                }
            }
        }
        else{
            answer = false;
        }

        return answer;
    }
}
