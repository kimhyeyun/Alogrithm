import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class 메뉴리뉴얼 {

    static ArrayList<String> combi;

    public static String[] solution(String[] orders, int[] course) {
        String[] answer;
        combi = new ArrayList<>();

        for(int i = 0; i < orders.length;i++){
            char[] tmp_ary = orders[i].toCharArray();
            Arrays.sort(tmp_ary);

            for(int idx = 0; idx < tmp_ary.length-1;idx++){
                for(int j = 0;j < course.length;j++){
                    dfs(tmp_ary, idx, 1, course[j], String.valueOf(tmp_ary[idx]));
                }
            }
        }

        HashMap<String, Integer> map = new HashMap<>();
        for(String key : combi){
            // map에 이미 key가 존재하면 +1
            map.put(key, map.getOrDefault(key,0) + 1);
        }

        ArrayList<String> keySetList = new ArrayList<>(map.keySet());
        Collections.sort(keySetList, (o1, o2) -> (map.get(o2).compareTo(map.get(o1))));

        ArrayList<String> anList = new ArrayList<>();
        for(int i = 0; i < course.length;i++){
            int max = 0;

            for(String key : keySetList){
                if(map.get(key) >= 2 && key.length() == course[i]){
                    if(map.get(key) >= max){
                        anList.add(key);
                        max = map.get(key);
                    }
                }
            }
        }

        Collections.sort(anList);
        answer = new String[anList.size()];
        anList.toArray(answer);

        return answer;
    }

    public static void dfs(char[] arr, int idx, int length, int course, String str){
        if(length == course){
            combi.add(str);
        }

        for(int i = idx+1;i < arr.length;i++){
            dfs(arr, i, length+1, course, str+arr[i]);
        }
    }

    public static void main(String[] args) {
        String[] o = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] c = {2,3,4};

        String[] r = solution(o, c);

        for(String a : r){
            System.out.println(a);
        }
    }
}
