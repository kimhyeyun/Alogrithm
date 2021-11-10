import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_Test_4 {
    public static int[] solution(String s) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        
        int cnt = 1;
        for(int i = 0; i < s.length(); i++){
            if(i == s.length()-1){
                if(s.charAt(i) == s.charAt(0)){
                    int a = list.remove(0);
                    list.add(0, a+cnt);
                }
            }
            else if(s.charAt(i) == s.charAt(i+1))
                cnt++;
            else{
                list.add(cnt);
                cnt = 1;
            }
        }

        Collections.sort(list);
        answer = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i++)  
            answer[i] = list.get(i);

        return answer;
    }

    public static void main(String[] args) {
        int[] answer = solution("aaabbaaa");
        for(int a : answer)
            System.out.println(a);
    }
}
