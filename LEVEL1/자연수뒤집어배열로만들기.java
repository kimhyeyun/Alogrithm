import java.util.LinkedList;
import java.util.List;

public class 자연수뒤집어배열로만들기 {
    public static void main(String[] args) {
        int[] ans = solution(12345);

        for(int a : ans){
            System.out.println(a);
        }
    }

    public static int[] solution(long n) {
        int[] answer = {};

        List<Integer> arr = new LinkedList<>();

        while(n != 0){
            int a = (int) (n % 10);
            arr.add(a);

            n /= 10;
        }

        answer = new int[arr.size()];
        int s = 0;
        for(int a : arr){
            answer[s++] = a;
        }

        return answer;
    }
    
}
