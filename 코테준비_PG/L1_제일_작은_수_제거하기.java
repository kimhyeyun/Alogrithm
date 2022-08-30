import java.util.ArrayList;
import java.util.List;

public class L1_제일_작은_수_제거하기 {
    public int[] solution(int[] arr) {
        if(arr.length == 1) return new int[]{-1};

        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < arr.length; i++) {
            if(min > arr[i]){
                min = arr[i];
                minIdx = i;
            }
        }

        arr[minIdx] = -1;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == -1) continue;
            list.add(arr[i]);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
