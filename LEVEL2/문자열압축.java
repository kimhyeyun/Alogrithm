
import java.util.Vector;

public class 문자열압축 {
    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
        
    }

    public static int solution(String s) {
        int answer  = s.length();
        Vector<String> token = new Vector<>();


        for(int i = 1;i <= s.length()/2;i++){
            token =  tokenize(s, i);

            String str = "";
            String before = token.get(0);
            int cnt = 1;

            for(int j = 1;j < token.size();j++){
                if(token.get(j).equals(before)){
                    cnt++;
                }
                else{
                    if(cnt != 1){
                        str += String.valueOf(cnt);
                    }
                    str += before;
                    before = token.get(j);
                    
                    cnt = 1;
                }
            }
            if(cnt != 1){
                str += String.valueOf(cnt);
            }

            str += before;
            answer = answer < str.length() ? answer : str.length();    

        }
        

        
        return answer;
    }

    private static Vector<String> tokenize(String s, int n) {
        Vector<String> t = new Vector<>();

        for(int i = 0;i < s.length();i+=n){
            if(i+n-1>=s.length())
                t.add(s.substring(i,s.length()));
            else 
                t.add(s.substring(i, i+n));
        }
        return t;
    }
}
