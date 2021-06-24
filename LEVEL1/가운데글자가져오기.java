public class 가운데글자가져오기 {
    public static void main(String[] args) {
        solution("qwer");
    }

    public static String solution(String s) {
        String answer = "";

        int l = s.length();

        if(l%2 == 0){
            // 짝수라면
            l /= 2;
            answer = s.substring(l-1, l+1);
        }

        else{
            //홀수
            l /= 2;
            answer = s.substring(l,l+1);
        }

        System.out.println(answer);
        return answer;
    }
}
