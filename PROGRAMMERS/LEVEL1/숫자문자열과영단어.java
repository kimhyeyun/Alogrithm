public class 숫자문자열과영단어 {
    public static void main(String[] args) {
        System.out.println(solution("one4seveneight"));
    }

    public static int solution(String s) {
        int answer = 0;
        String ans = "";

        for(int i=0; i<s.length();i++){
            if('0' <= s.charAt(i) && s.charAt(i) <= '9')
                ans += s.charAt(i);

            else{
                if(s.charAt(i) == 'z'){
                    ans += '0';
                    i += 3;
                }
                else if(s.charAt(i) == 'o'){
                    ans += '1';
                    i += 2;
                }
                else if(s.charAt(i) == 'e'){
                    ans += '8';
                    i += 4;
                }
                else if(s.charAt(i) == 'n'){
                    ans += '9';
                    i += 3;
                }
                else if(s.charAt(i) == 't'){
                    if(s.charAt(i+1) == 'w'){
                        ans += '2';
                        i += 2;
                    }
                    else{
                        ans += '3';
                        i += 4;
                    }
                }
                else if(s.charAt(i) == 'f'){
                    if(s.charAt(i+1) == 'o'){
                        ans += '4';
                        i += 3;
                    }
                    else{
                        ans += '5';
                        i += 3;
                    }
                }
                else{
                    if(s.charAt(i+1) == 'i'){
                        ans += '6';
                        i += 2;
                    }
                    else{
                        ans += '7';
                        i += 4;
                    }
                }
            }
        }

        answer = Integer.parseInt(ans);
        return answer;
    }
}
