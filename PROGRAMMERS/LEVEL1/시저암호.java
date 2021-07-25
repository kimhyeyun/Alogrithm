public class 시저암호 {
    public static void main(String[] args) {
        System.out.println(solution("a B z", 4));
    }

    public static String solution(String s, int n) {
        String answer = "";

        for(int i = 0;i < s.length();i++){
            if(s.charAt(i) == ' ')
                answer += " ";
            else {
                char start = (s.charAt(i) >= 'a') ? 'a' : 'A';
                start += (s.charAt(i) - start + n) % 26;
                answer += start;
            }
        }

        return answer; 
    }

    // 다른 사람 풀이 : 속도는 유사함
    public String ceaser(String s, int n){
        String answer = "";
        n = n % 26;
        for(int i = 0;i < s.length();i++){
            char ch = s.charAt(i);

            if(Character.isLowerCase(ch)){
                ch = (char)((ch - 'a' + n) % 26 + 'a');
            }
            else if(Character.isUpperCase(ch)){
                ch = (char)((ch - 'A' + n) % 26 + 'A');
            }

            answer += ch;
        }

        return answer;
    }
}
