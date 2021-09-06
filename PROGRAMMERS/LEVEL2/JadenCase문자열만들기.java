public class JadenCase문자열만들기 {
    public static String solution(String s) {
        String answer = "";

        s = s.toLowerCase();
        
        int idx = 0;
        for(char c : s.toCharArray()){
            if(idx == 0 && ('a' <= c && c <= 'z')){
                answer += (char)(c-32);
                idx++;
            }
            
            else if(c == ' '){
                answer += ' ';
                idx = 0;
            }
            
            else{
                answer += c;
                idx++;
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution("3people unFollowed me"));
    }
}
