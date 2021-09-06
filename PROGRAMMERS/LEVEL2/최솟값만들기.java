import java.util.Arrays;
import java.util.Collections;

public class 최솟값만들기 {

    public static int solution(int []A, int []B)
    {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for(int i = 0 ; i < A.length; i++){
            answer += (A[i] * B[B.length-1-i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] A = {1,2};
        int[] B = {3,4};
        System.out.println(solution(A, B));
    }
}
