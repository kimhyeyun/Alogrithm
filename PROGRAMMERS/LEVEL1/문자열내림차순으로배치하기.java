import java.util.Arrays;

public class 문자열내림차순으로배치하기 {
    public static void main(String[] args) {
        System.out.println(solution("Zbcdefg"));
    }

    // 내 코드 -> 대문자와 소문자를 분리해서 저장 후, 정렬하고, 뒤집어서 붙임 --> 굉장히 느림
    public static String solution(String s) {
        String answer = "";
        
        StringBuilder str = new StringBuilder(s);
        String mini = "";

        for(int i = 0;i<str.length();i++){
            if('a' <= str.charAt(i) && str.charAt(i) <= 'z'){
                mini += str.charAt(i);
                str = str.deleteCharAt(i);
                i--;
            }
        }

        char[] m = mini.toCharArray();
        Arrays.sort(m);
        

        String big = str.toString();
        char[] b = big.toCharArray();
        Arrays.sort(b);

        for(int i = m.length-1;i>= 0; i--)
            answer += m[i];

        for(int i = b.length-1; i>=0; i--){
            answer += b[i];
        }

        return answer;
    }

    // 다른사람 풀이
    public String reverseStr(String str){
        char[] s = str.toCharArray();
        Arrays.sort(s);

        return new StringBuilder(new String(s)).reverse().toString();
    }
}

