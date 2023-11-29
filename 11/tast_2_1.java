import java.util.ArrayList;
import java.util.List;

public class tast_2_1 {
    static int answer;
    static public int solution(int[] A) {
        // write your code in Java SE 8
        for (int cnt = A.length; cnt > 0; cnt--) {
            makeSubSet(A, cnt, 0,0, new boolean[A.length]);
            if(answer > 0){ return cnt;}

        }
        return 0;
    }

    static private void makeSubSet(int[] a, int sum, int cnt, int idx, boolean[] isPicked) {
        if (sum == cnt) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < isPicked.length; i++) {
                if(!isPicked[i]) continue;
                list.add(a[i]);
            }
            int result = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                result = result & list.get(i);
            }

            if(result > 0) answer = result;
            return;
        }

        for (int i = idx; i < a.length; i++) {
            if(isPicked[i]) continue;

            isPicked[i] = true;
            makeSubSet(a, sum, cnt + 1, i + 1, isPicked);
            isPicked[i] = false;
        }
        return;
    }
    public static void main(String[] args) {
        int[] A = {13, 7 ,2, 8,3};
        System.out.println(solution(A));
    }
}
