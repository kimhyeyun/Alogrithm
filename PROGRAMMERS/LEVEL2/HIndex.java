import java.util.Arrays;
import java.util.Collections;

public class HIndex {
    
    public static int solution(int[] citations) {

        int answer = 0;

        Integer[] c = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        Arrays.sort(c, Collections.reverseOrder());

        if(c[0] == 0)
            return 0;

        for(int h = 0; h < c.length; h++){
            if(c[h] > h){
                answer++;
            }
            else{
                break;
            }
        }

        return answer;
    }

    /* 다른 사람 풀이 */
    /* 
        원소의 값은 점점 작아지고, 그 값 이상인 것도 점점 감소하므로, 이 두 값 중 최소값의 변화가 증가하다가 감소하는 지점을 찾으면 그 것이 H-Index
    */
    public static int otherSolution(int[] citations) {

        Arrays.sort(citations);

        int max = 0;
        for(int i = citations.length-1; i > -1; i--){
            int min = Math.min(citations[i], citations.length-i);

            if(max <= min)
                max = min;
            else
                break;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] c = {3,0,6,1,5};

        System.out.println(otherSolution(c));
    }
}
