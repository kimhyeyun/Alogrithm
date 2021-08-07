import java.util.ArrayList;
import java.util.List;

public class 영어끝말잇기 {

    public static int[] solution(int n, String[] words) {
        int[] answer = {0, 0};

        List<String> list = new ArrayList<>();

        for(int i = 0; i < words.length; i++){
            // 3. 앞사람이 말한 단어의 마지막 문자로 시작해야함
            // 처음은 고려할 필요x
            if(i != 0 && words[i].charAt(0) != words[i-1].charAt(words[i-1].length()-1)){
                answer[0] = i%n + 1;
                answer[1] = i/n + 1;
                break;
            }

            // 4. 이전에 등장한 단어인가
            if(list.contains(words[i])){
                answer[0] = i%n + 1;
                answer[1] = i/n + 1;
                break;
            }
            
            list.add(words[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] w = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int[] ans = solution(3, w);

        for(int a : ans){
            System.out.println(a);
        }
    }
}
