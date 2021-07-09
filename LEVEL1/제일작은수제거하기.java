import java.util.Arrays;

public class 제일작은수제거하기 {
    public static void main(String[] args) {
        int[] arr = {10
        };
        int[] ans = solution(arr);

        for(int a : ans){
            System.out.println(a);
        }
    }

    public static int[] solution(int[] arr) {
        int[] answer = {};

        int min;
        if(arr.length == 1){
            answer = new int[1];
            answer[0] = -1;
        }

        else{
            min = arr[0];
            for(int i = 1 ;i<arr.length;i++){
                if(min > arr[i]){
                    min = arr[i];
                }
            }
            answer = new int[arr.length-1];
            int i = 0;

            for(int a : arr){
                if(a == min){
                    continue;
                }
                else{
                    answer[i++] = a;
                }
            }
        }
        
        
        
        return answer;
    }

    // 다른사람 코드
    public int[] delMin(int[] arr){
        if(arr.length <= 1)
            return new int[]{-1};
        int min = Arrays.stream(arr).min().getAsInt();
        return Arrays.stream(arr).filter(i -> i != min).toArray();
    }
    
}
