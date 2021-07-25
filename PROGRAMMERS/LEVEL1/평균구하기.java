

public class 평균구하기 {

    public static void main(String[] args) {
        int arr[] = {1,2,3,4};

        System.out.println(solution(arr));
    }

    public static double solution(int[] arr) {
        double answer = 0;
        
        for(int a : arr){
            answer += a;
        }

        answer /= arr.length;

        return answer;
    }
}
