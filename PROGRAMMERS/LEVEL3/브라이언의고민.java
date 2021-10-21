import java.util.ArrayList;
import java.util.List;

/* 

    10/21 ThursDay
    브라이언의 고민 
    FAILED -> 뭐가 틀린거???????? 아놔

*/

public class 브라이언의고민 {
    public static String solution(String sentence) {
        String answer = "";
        String invalid = "Invalid";

        List<String> list = new ArrayList<>();
        char ch = ' ';
        while(sentence.length() > 0){
            // 규칙 1
            if(!checkLetter(sentence, 0)){
                //규칙 1이 중복 적용된 경우
                if(!checkLetter(sentence, 1))
                    return invalid;

                // 특수기호
                ch = sentence.charAt(1);
                int specialIdx = -1;
                
                for(int i = 2; i < sentence.length() ; i++){
                    if(checkLetter(sentence, i)){
                        if(sentence.charAt(i) != ch) break;
                        specialIdx = i;
                    }
                }

                if(specialIdx == -1) return invalid;    

                String str = sentence.substring(0, specialIdx+2);
                str = str.replace(ch, ' ');
                list.add(str.replace(" ", ""));

                sentence = sentence.substring(specialIdx + 2);
            }

            // 규칙 2
            else{
                // 규칙 2 중복 적용
                if(checkLetter(sentence, 1)) return invalid;

                ch = sentence.charAt(0);
                int specialIdx = -1;

                for(int i = 1; i < sentence.length() ; i++){
                    if(checkLetter(sentence, i)){
                        if(sentence.charAt(i) == ch) {
                            specialIdx = i;
                            break;
                        }
                    }
                }

                if(specialIdx == -1)    return invalid;

                String str = sentence.substring(0, specialIdx+1);
                str = str.replace(ch, ' ');
                list.add(str.replace(" ", ""));

                sentence = sentence.substring(specialIdx+1);
            }
        }


        for(int i = 0 ; i < list.size() ; i++){
            if(i == list.size()-1)
                answer += list.get(i);
            else
                answer += list.get(i) + " ";
        }

        return answer;
    }

    public static boolean checkLetter(String str, int idx){
        if('a' <= str.charAt(idx) && str.charAt(idx) <= 'z')
            return true;
        
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solution("HaEaLaLaObWORLDb"));
    }
}
