import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class 후보키 {
/*     static ArrayList<HashSet<Integer>> candidateKey;

    public static int solution(String[][] relation){
        candidateKey = new ArrayList<>();
        int colSize = relation[0].length;

        for(int i = 1; i <= colSize;i++){
            makeKeySet(-1, colSize-1, 0, i, new HashSet<>(), relation);
        }

        return candidateKey.size();
        
    }


    private static void makeKeySet(int attr, int maxAttr, int idx, int size, HashSet<Integer> keySet, String[][] relation) {

        if(idx == size){

            for(HashSet<Integer> key : candidateKey){
                if(keySet.containsAll(key)){
                    return;
                }
            }

            if(isUnique(keySet, relation)){
                candidateKey.add(keySet);
            }
            
            return;
        }

        for(int i = attr+1; i <= maxAttr;i++){
            HashSet<Integer> newKeySet = new HashSet<>(keySet);
            newKeySet.add(i);
            makeKeySet(i, maxAttr, idx+1, size, newKeySet, relation);
        }
    }


    private static boolean isUnique(HashSet<Integer> keySet, String[][] relation) {
        HashMap<String, String> map = new HashMap<>();

        for(int r = 0; r < relation.length;r++){
            String key = "";

            for(int c : keySet){
                key += relation[r][c];
            }

            if(map.containsKey(key))
                return false;
            else    
                map.put(key,key);
        }

        return true;
    } */


    // 비트마스크를 이용한 코드
    public static int bitmask_solution(String[][] relation){
        
        ArrayList<Integer> candidateKey = new ArrayList<>();

        int rowLen = relation.length;
        int colLen = relation[0].length;

        for(int set = 1; set < (1 << colLen); set++){
            // 최소성 검사
            if(!isMinimal(set, candidateKey))
                continue;
            
            // 유일성 검사
            if(isUnique(set, rowLen, colLen, candidateKey, relation)){
                candidateKey.add(set);
            }
        }

        return candidateKey.size();
    }


    private static boolean isUnique(int set, int rowLen, int colLen, ArrayList<Integer> candidateKey,
            String[][] relation) {
        
            HashMap<String, String> map = new HashMap<>();

            for(int row = 0; row < rowLen;row++){
                String dataByKetSet = "";

                for(int th = 0; th < colLen; th++){
                    if((set & (1 << th)) != 0){
                        dataByKetSet += relation[row][th];
                    }
                }

                if(map.containsKey(dataByKetSet))
                    return false;
                else
                    map.put(dataByKetSet, dataByKetSet);
            }

            return true;
    }


    private static boolean isMinimal(int set, ArrayList<Integer> candidateKey) {
        for(int key : candidateKey){
            if((key & set) == key) 
                return false;
        }

        return true;
    }


    public static void main(String[] args) {
        String r[][] = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        
        System.out.println(bitmask_solution(r));
    }
}
