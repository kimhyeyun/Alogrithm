import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class 순위검색 {

    static Map<String, ArrayList<Integer>> infoMap = new HashMap<>();

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        // key 만들기
        for(int i = 0; i < info.length; i++){
            makeKey(info[i]);
        }

        // value 값 정렬
        ArrayList<String> keySet = new ArrayList<>();
        for(Map.Entry<String, ArrayList<Integer>> entry : infoMap.entrySet()){
            if(keySet.contains(entry.getKey())){
                continue;
            }
            else{
                keySet.add(entry.getKey());
                Collections.sort(entry.getValue());
            }
        }
        
        // query 처리 부분
        for(int i = 0; i < query.length; i++){
            String[] temp = query[i].split(" ");

            String key = temp[0] + temp[2] + temp[4] + temp[6];

            if(!infoMap.containsKey(key)){
                answer[i] = 0;
                continue;
            }

            ArrayList<Integer> arr = infoMap.get(key);

            if("-".equals(temp[7])){
                answer[i] = arr.size();
            }
            else{
                int score = Integer.parseInt(temp[7]);
                // 이분탐색
                answer[i] = binarySearch(arr, score);
            }
        }
        
        return answer;
    }

    private static int binarySearch(ArrayList<Integer> arr, int score) {
        int start = 0;
        int mid = 0;
        int end = arr.size();

        while(end > start){
            mid = (start + end) / 2;
            
            if(arr.get(mid) >= score){
                end = mid;
            }
            else 
                start = mid + 1;
        }

        return arr.size() - start;
        
    }

    private static void makeKey(String info) {
        String[] temp = info.split(" ");

        String[] language = {temp[0], "-"};
        String[] part = {temp[1], "-"};
        String[] career = {temp[2], "-"};
        String[] food = {temp[3], "-"};
        int score = Integer.parseInt(temp[4]);

        for(int i = 0; i < language.length; i++){
            for(int j = 0 ;j < part.length; j++){
                for(int k = 0 ; k < career.length; k++){
                    for(int p = 0; p < food.length; p++){
                        String key = language[i] + part[j] + career[k] + food[p];

                        if(infoMap.containsKey(key)){
                            ArrayList<Integer> arr = infoMap.get(key);
                            arr.add(score);
                            infoMap.put(key, arr);
                        }else{
                            ArrayList<Integer> arr = new ArrayList<>();
                            arr.add(score);
                            infoMap.put(key, arr);
                        }
                    }
                }
            }
        }
        return;
    }

    public static void main(String[] args) {
        String[] i = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] q = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        solution(i, q);
    }
}
