import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main_P_5주차_모음사전{

    static List<String> vowels = List.of("A", "E", "I", "O", "U");
    static Map<String, Integer> dicMap = new HashMap<>();
    static int orderNum = 1;

    public static int solution(String word) {
        
        initDicMap(dicMap, new LinkedList<>());

        return dicMap.get(word);
    }
    private static void initDicMap(Map<String, Integer> dicMap, List<String> list) {
        String word = String.join("", list);

        if(word.length() != 0)
            dicMap.put(word, orderNum++);
        
        if(word.length() <= 4){
            for(int i = 0 ; i < vowels.size() ; i++){
                list.add(vowels.get(i));
                initDicMap(dicMap, list);
                list.remove(list.size()-1);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(solution("AAAAE"));
    }
}