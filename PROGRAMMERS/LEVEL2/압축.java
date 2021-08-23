import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class 압축 {
    public static int[] solution(String msg) {
        ArrayList<Integer> list = new ArrayList<>();
        Map<String, Integer> dictMap = new HashMap<>();
        int idx = 1;

        for(char a = 'A'; a <= 'Z'; a++ ){
            dictMap.put(a+"", idx++);
        }

        for(int i = 0; i < msg.length(); i++){
            int num = 1;

            while(i+num <= msg.length() && dictMap.containsKey(msg.substring(i, i+num))){
                num++;
            }

            if(i+num > msg.length()){
                list.add(dictMap.get(msg.substring(i)));
                break;
            }

            list.add(dictMap.get(msg.substring(i, i+num-1)));
            dictMap.put(msg.substring(i,i+num), idx++);
            i += num-2;
        }

        int[] answer = new int[list.size()];
        int i = 0;
        for(int l : list){
            answer[i++] = l;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] ans =  solution("KAKAO");
        for(int a : ans){
            System.out.println(a);
        }
    }
}
