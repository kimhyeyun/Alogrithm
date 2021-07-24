import java.util.Stack;

public class 괄호변환 {
    
    /* 
        (,)의 갯수가 같다면 : 균형잡힌 괄호 문자열
        여기에 짝도 맞다면 : 올바른 괄호 문자열

        문자열 w가 균형잡힌 괄호 문자열이라면
        1. 빈문자열 일경우, 빈문자열 반환
        2. w를 두 균형잡힌 괄호 문자열 u, v로 분리 (u는 더 이상 분리x, v는 빈문자열일 수 있음)
        3. u가 올바른 괄호 문자열 이라면, v에대해  1단계 부터 다시 수행
        3-1. 결과 문자열을 u에 이어 붙인 후 반환
        4. u가 올바른 괄호 문자열이 아니라면, 
        4-1. 빈 문자열에 첫번째 문자로 ( 붙임
        4-2. v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙임
        4-3. )를 부팀
        4-4. u의 첫번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙임
        4-5. 생성된 문자열 반환
    */

    static int pos;

    public static String solution(String p) {
        String answer = "";

        if(p.isEmpty())
            return p;
        
        boolean correct = isCorrect(p);

        String u = p.substring(0,pos);
        String v = p.substring(pos, p.length());

        // 만약, u가 올바른 괄호 문자열이라면,
        if(correct){
            // v에 대해서 1단계부터 수행
            // u에 이어 붙여서 반환
            return u + solution(v);
        }

        // u가 올바른 괄호 문자열이 아니라면
        // ( ) 사이에 v에대해 1단계부터 재귀적으로 수행
        answer = "("+solution(v)+")";

        // 첫번째, 마지막 문자 제외하고 뒤집어서 붙이기
        for(int i  = 1;i<u.length()-1;i++){
            if(u.charAt(i) == '(')
                answer += ')';
            else
                answer += '(';
        }
        
        return answer;
    }

    private static boolean isCorrect(String p) {
        boolean ret = true;
        int left = 0;
        int right = 0;
        Stack<Character> stk = new Stack<>();

        for(int i = 0;i < p.length();i++){
            if(p.charAt(i) == '('){
                left++;
                stk.push('(');
            }
            else{
                right++;
                if(stk.isEmpty()){
                    ret = false;
                }
                else{
                    stk.pop();
                }
            }

            if(left == right){
                pos = i+1;
                return ret;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(solution("()))((()"));
    }


}
