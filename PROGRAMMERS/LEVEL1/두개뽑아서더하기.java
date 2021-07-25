
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class 두개뽑아서더하기 {
    public static void main(String[] args) {
        int n[] = {2,1,3,4,1};
        int ans[] = solution(n);

        for(int i=0;i<ans.length;i++){
            System.out.println(ans[i]);
        }
    }

    public static int[] solution(int[] numbers) {
        // 중복된 값을 저장하지 않기 위해 hashset 사용
        HashSet<Integer> list = new HashSet<>();
        
        for(int i=0;i<numbers.length;i++){
            for(int j=i+1;j<numbers.length;j++){
                list.add(numbers[i]+numbers[j]);
            }
        }

        int[] answer = new int[list.size()];
        Iterator<Integer> it = list.iterator();
        int i = 0;
        while(it.hasNext()){
            answer[i++] = it.next();
        }

        Arrays.sort(answer);

        return answer;
    }
}
