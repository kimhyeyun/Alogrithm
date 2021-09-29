
import java.util.TreeMap;

public class 이중우선순위큐 {
    public static int[] solution(String[] operations) {

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for(String o : operations){
            String[] command = o.split(" ");

            if(command[0].equals("I")){
                // 삽입
                int x = Integer.parseInt(command[1]);

                treeMap.put(x, treeMap.getOrDefault(x, 0)+1);
            }
            else{
                if(treeMap.isEmpty())
                    continue;
                if(command[1].equals("1")){
                    // 최댓값 삭제
                    int max = treeMap.lastKey();
                    if(treeMap.get(max) == 1)
                        treeMap.remove(max);
                    else
                        treeMap.put(max, treeMap.get(max)-1);
                }
                else{
                    // 최솟값 삭제 
                    int min = treeMap.firstKey();
                    if(treeMap.get(min) == 1)
                        treeMap.remove(min);
                    else
                        treeMap.put(min, treeMap.get(min)-1);
                }
            }
        }

        if(treeMap.isEmpty())
            return new int[]{0,0};
        else
            return new int[]{treeMap.lastKey(), treeMap.firstKey()};
    }

    public static void main(String[] args) {
        String[] operations = {"I 7","I 5","I -5","D -1"};
        int[] answer = solution(operations);

        for(int a : answer){
            System.out.println(a);
        }
    }
}
