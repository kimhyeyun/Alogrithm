import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavavsC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();

        boolean isJava = isJava(input);
        boolean isCpp = isCpp(input);

        if(isJava){
            // C++ 형식으로 변경
            for(char c : input.toCharArray()){
                if('A' <= c && c <= 'Z'){
                    // 대문자가 나오면 _ 추가하고, 소문자로 변경
                    sb.append("_");
                    sb.append((char)(c + 32));
                }
                else
                    sb.append(c);
            }
        }

        else if(isCpp){
            // Java 형식으로 변경
            char[] str = input.toCharArray();

            for(int i = 0 ; i < str.length; i++){
                // _ 가 나오면
                if(str[i] == '_'){
                    // 인덱스를 증가시켜 다음 글자를 대문자로 변경ㅌㅌ
                    i++;
                    sb.append((char)(str[i] - 32));
                }
                else{
                    sb.append(str[i]);
                }
            }
        }

        // 둘 다 아니면 Error
        else{
            sb.append("Error!");
        }

        System.out.println(sb);

    }

    private static boolean isCpp(String input) {
        // _ 가 두개 연속
        if(input.contains("__"))
            return false;
        
        // 첫글자 또는 마지막 글자가 _
        if(input.charAt(input.length()-1) == '_' || input.charAt(0) == '_')
            return false;
        
        // 대문자가 포함
        for(char c : input.toCharArray()){
            if('A' <= c && c <= 'Z')
                return false;
        }

        return true;
    }

    private static boolean isJava(String input) {

        // 첫글자가 대문자
        if('A' <= input.charAt(0) && input.charAt(0) <= 'Z')
            return false;

        // _가 포함
        for(char c : input.toCharArray()){
            if(c == '_')
                return false;
        }

        return true;
    }
    
}
