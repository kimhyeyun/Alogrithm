import java.util.ArrayList;
import java.util.Arrays;

public class 나누어떨어지는숫자배열 {
    public static void main(String[] args) {
        int[] arr = {5,9,7,10};
        int[] a = solution(arr, 5);
        for(int i : a){
            System.out.println(i);;
        }
    }
    public static int[] solution(int[] arr, int divisor) {
        int[] answer = {};

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i] % divisor == 0)
                list.add(arr[i]);
        }

        if(list.size() == 0)
            list.add( -1);
        answer = new int[list.size()];
        int i = 0;
        for(int a : list){
            answer[i++] = a;
            
        }

        Arrays.sort(answer);
    

        return answer;
    }    
}
